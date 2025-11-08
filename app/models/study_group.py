from sqlalchemy import Column, String, Integer, TIMESTAMP, ForeignKey, text
from sqlalchemy.orm import relationship
from app.core.database import Base

class StudyGroup(Base):
    __tablename__ = "study_groups"

    id = Column(Integer, primary_key=True, index=True)
    course_id = Column(Integer, ForeignKey("courses.id", ondelete="CASCADE"))
    created_by = Column(ForeignKey("users.id", ondelete="SET NULL"))
    description = Column(String)
    created_at = Column(TIMESTAMP, server_default=text("NOW()"))

    # Relationships
    course = relationship("Course", back_populates="study_groups")
    creator = relationship("User", back_populates="study_groups")
    members = relationship("GroupMember", back_populates="group")
