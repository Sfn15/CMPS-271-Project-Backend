from fastapi import APIRouter, Depends
from sqlalchemy.orm import Session
from app.core.database import get_db
from app.models.study_group import StudyGroup
from app.models.course import Course
from app.models.user import User

router = APIRouter(prefix="/study-groups", tags=["Study Groups"])

# ✅ Get study groups for a course
@router.get("/{course_code}")
def get_study_groups(course_code: str, db: Session = Depends(get_db)):
    groups = (
        db.query(StudyGroup, User.name.label("creator_name"))
        .join(Course, StudyGroup.course_id == Course.id)
        .join(User, StudyGroup.created_by == User.id)
        .filter(Course.course_code == course_code)  # ✅ fixed
        .all()
    )

    return [
        {
            "id": g.StudyGroup.id,
            "creator_name": g.creator_name,
            "description": g.StudyGroup.description,  # we store link here
        }
        for g in groups
    ]


# ✅ Add a study group

@router.post("/{course_code}")
def create_study_group(course_code: str, data: dict, db: Session = Depends(get_db)):
    created_by = data.get("user_id")
    link = data.get("link")  # stored in description

    # Find course ID from course_code
    course = db.query(Course).filter(Course.course_code == course_code).first()
    if not course:
        return {"error": "Course not found"}

    new_group = StudyGroup(
        course_id=course.id,
        created_by=created_by,
        description=link
    )

    db.add(new_group)
    db.commit()
    db.refresh(new_group)

    return {"message": "Study group created ✅"}
