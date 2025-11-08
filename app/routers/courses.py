from fastapi import APIRouter, Depends
from sqlalchemy.orm import Session
from app.core.database import get_db
from app.models.course import Course

router = APIRouter(prefix="/courses", tags=["Courses"])

@router.get("/")
def get_courses(db: Session = Depends(get_db)):
    courses = db.query(Course).all()
    return [{"code": c.course_code, "title": c.title} for c in courses]

