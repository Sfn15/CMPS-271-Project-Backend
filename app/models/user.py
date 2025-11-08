from sqlalchemy import Column, String, TIMESTAMP, text
from sqlalchemy.dialects.postgresql import UUID
from sqlalchemy.orm import relationship
from app.core.database import Base

class User(Base):
    __tablename__ = "users"

    id = Column(UUID(as_uuid=True), primary_key=True, server_default=text("gen_random_uuid()"))
    email = Column(String, unique=True, nullable=False)
    name = Column(String)
    major = Column(String)
    year = Column(String)
    role = Column(String, default="student")  # student | tutor | admin
    created_at = Column(TIMESTAMP, server_default=text("NOW()"))

    # ✅ Fix: explicitly tell SQLAlchemy which FK to use
    documents = relationship("Document", back_populates="uploader", foreign_keys="Document.uploaded_by")

    study_groups = relationship("StudyGroup", back_populates="creator")
