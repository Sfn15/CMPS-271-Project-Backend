from fastapi import APIRouter
from supabase import create_client
import os
from app.schemas.tutors import TutorCreate

router = APIRouter(prefix="/tutors", tags=["Tutors"])

SUPABASE_URL = os.getenv("SUPABASE_URL")
SUPABASE_SERVICE_ROLE = os.getenv("SUPABASE_SERVICE_ROLE")
supabase = create_client(SUPABASE_URL, SUPABASE_SERVICE_ROLE)

# ✅ Add tutor
@router.post("/")
def add_tutor(data: TutorCreate):
    tutor_data = {
        "user_id": data.user_id,
        "phone": data.phone,
        "course_code": data.course_code,
    }
    response = supabase.table("tutors").insert(tutor_data).execute()
    return {"message": "Tutor added ✅", "data": response.data}

# ✅ Get tutors (return raw data like before)
@router.get("/{course_code}")
def get_tutors(course_code: str):
    response = (
        supabase.table("tutors")
        .select("id, phone, course_code, user_id, users(name)")
        .eq("course_code", course_code)
        .execute()
    )
    return response.data

