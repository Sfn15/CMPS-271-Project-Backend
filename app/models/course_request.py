from sqlalchemy import Column, String, Integer, TIMESTAMP, ForeignKey, text
from app.core.database import Base
from sqlalchemy.orm import relationship

class CourseRequest(Base):
    __tablename__ = "course_requests"

    id = Column(Integer, primary_key=True, index=True)
    user_id = Column(ForeignKey("users.id", ondelete="CASCADE"))
    course_id = Column(ForeignKey("courses.id", ondelete="CASCADE"))
    request_type = Column(String)  # tutor | study_buddy
    status = Column(String)        # pending | accepted | rejected
    created_at = Column(TIMESTAMP, server_default=text("NOW()"))

    # Relationships
    user = relationship("User")
    course = relationship("Course")
