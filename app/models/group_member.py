from sqlalchemy import Column, ForeignKey, Integer
from sqlalchemy.orm import relationship
from app.core.database import Base

class GroupMember(Base):
    __tablename__ = "group_members"

    group_id = Column(Integer, ForeignKey("study_groups.id", ondelete="CASCADE"), primary_key=True)
    user_id = Column(ForeignKey("users.id", ondelete="CASCADE"), primary_key=True)

    # Relationships
    group = relationship("StudyGroup", back_populates="members")
    user = relationship("User")
