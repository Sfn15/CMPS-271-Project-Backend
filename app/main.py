from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from app.core.database import Base, engine
from app.models import *
from supabase import create_client
from dotenv import load_dotenv
from pathlib import Path
import os

# ✅ Load environment variables
env_path = Path(__file__).resolve().parent.parent / ".env"
load_dotenv(dotenv_path=env_path)

SUPABASE_URL = os.getenv("SUPABASE_URL")
SUPABASE_SERVICE_ROLE = os.getenv("SUPABASE_SERVICE_ROLE")

print("✅ ENV LOADED" if SUPABASE_URL and SUPABASE_SERVICE_ROLE else "❌ ENV NOT LOADED")

# ✅ Connect to Supabase
supabase = create_client(SUPABASE_URL, SUPABASE_SERVICE_ROLE)

# ✅ FastAPI app
app = FastAPI(title="CourseConnect API")

# ✅ Make sure all models inside app/models/ are registered
from app.models import *

# ✅ Create DB tables before registering routers
Base.metadata.create_all(bind=engine)

# ✅ Register routers
from app.routers import tutors, courses , study_groups
app.include_router(tutors.router)
app.include_router(courses.router)
app.include_router(study_groups.router)

# ✅ CORS
origins = ["http://localhost:3000", "http://127.0.0.1:3000"]
app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

@app.get("/")
def home():
    return {"message": "CourseConnect API is running ✅"}

@app.get("/users")
def get_users():
    response = supabase.table("users").select("*").execute()
    return response.data
