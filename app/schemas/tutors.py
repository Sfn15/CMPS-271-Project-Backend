from pydantic import BaseModel

class TutorCreate(BaseModel):
    user_id: str
    phone: str
    course_code: str
