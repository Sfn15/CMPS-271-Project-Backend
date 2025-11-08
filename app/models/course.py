from sqlalchemy import Column, String
from sqlalchemy.dialects.postgresql import UUID
from sqlalchemy.sql import text
from app.core.database import Base
from sqlalchemy.orm import relationship

class Course(Base):
    __tablename__ = "courses"

    id = Column(UUID(as_uuid=True), primary_key=True, server_default=text("gen_random_uuid()"))
    course_code = Column(String, unique=True, nullable=False)
    title = Column(String, nullable=False)
  

    # Relationships
    study_groups = relationship("StudyGroup", back_populates="course")
    documents = relationship("Document", back_populates="course")

