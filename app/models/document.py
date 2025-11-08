from sqlalchemy import Column, String, TIMESTAMP, ForeignKey, text
from sqlalchemy.dialects.postgresql import UUID
from sqlalchemy.orm import relationship
from app.core.database import Base

class Document(Base):
    __tablename__ = "documents"

    id = Column(UUID(as_uuid=True), primary_key=True, server_default=text("gen_random_uuid()"))
    course_id = Column(ForeignKey("courses.id", ondelete="CASCADE"))
    uploaded_by = Column(ForeignKey("users.id", ondelete="SET NULL"))
    title = Column(String, nullable=False)
    file_path = Column(String, nullable=False)
    file_type = Column(String)
    description = Column(String)
    status = Column(String, default="pending")  # pending | approved | rejected
    reviewed_by = Column(ForeignKey("users.id"))
    reviewed_at = Column(TIMESTAMP)
    created_at = Column(TIMESTAMP, server_default=text("NOW()"))

    # Relationships
    course = relationship("Course", back_populates="documents")
    uploader = relationship("User", back_populates="documents", foreign_keys=[uploaded_by])
    reviewer = relationship("User", foreign_keys=[reviewed_by])
