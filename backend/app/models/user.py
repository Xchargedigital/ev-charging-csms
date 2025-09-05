#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
EV Charging CSMS Platform - User Model
电动汽车充电桩管理系统 - 用户模型
"""

from sqlalchemy import Column, Integer, String, Boolean, DateTime, Float, Text
from sqlalchemy.sql import func
from sqlalchemy.orm import relationship
from app.core.database import Base


class User(Base):
    """用户模型"""
    __tablename__ = "users"
    
    id = Column(Integer, primary_key=True, index=True)
    username = Column(String(50), unique=True, index=True, nullable=False)
    email = Column(String(100), unique=True, index=True, nullable=False)
    phone = Column(String(20), unique=True, index=True, nullable=False)
    hashed_password = Column(String(255), nullable=False)
    
    # 用户信息
    full_name = Column(String(100))
    avatar = Column(String(255))
    gender = Column(String(10))
    birth_date = Column(DateTime)
    
    # 账户状态
    is_active = Column(Boolean, default=True)
    is_verified = Column(Boolean, default=False)
    is_premium = Column(Boolean, default=False)
    
    # 余额和积分
    balance = Column(Float, default=0.0)
    points = Column(Integer, default=0)
    
    # 时间戳
    created_at = Column(DateTime, default=func.now())
    updated_at = Column(DateTime, default=func.now(), onupdate=func.now())
    last_login = Column(DateTime)
    
    # 关联关系
    charging_sessions = relationship("ChargingSession", back_populates="user")
    payments = relationship("Payment", back_populates="user")
    vehicles = relationship("Vehicle", back_populates="owner")


class Vehicle(Base):
    """车辆模型"""
    __tablename__ = "vehicles"
    
    id = Column(Integer, primary_key=True, index=True)
    user_id = Column(Integer, nullable=False, index=True)
    license_plate = Column(String(20), unique=True, index=True, nullable=False)
    brand = Column(String(50))
    model = Column(String(50))
    year = Column(Integer)
    color = Column(String(20))
    battery_capacity = Column(Float)  # 电池容量(kWh)
    max_charging_power = Column(Float)  # 最大充电功率(kW)
    
    # 时间戳
    created_at = Column(DateTime, default=func.now())
    updated_at = Column(DateTime, default=func.now(), onupdate=func.now())
    
    # 关联关系
    owner = relationship("User", back_populates="vehicles")
    charging_sessions = relationship("ChargingSession", back_populates="vehicle")


class UserAddress(Base):
    """用户地址模型"""
    __tablename__ = "user_addresses"
    
    id = Column(Integer, primary_key=True, index=True)
    user_id = Column(Integer, nullable=False, index=True)
    name = Column(String(100), nullable=False)  # 地址名称
    contact_name = Column(String(50))  # 联系人姓名
    contact_phone = Column(String(20))  # 联系人电话
    
    # 地址信息
    province = Column(String(50))
    city = Column(String(50))
    district = Column(String(50))
    street = Column(String(200))
    detail_address = Column(Text)
    postal_code = Column(String(10))
    
    # 坐标
    latitude = Column(Float)
    longitude = Column(Float)
    
    # 状态
    is_default = Column(Boolean, default=False)
    is_active = Column(Boolean, default=True)
    
    # 时间戳
    created_at = Column(DateTime, default=func.now())
    updated_at = Column(DateTime, default=func.now(), onupdate=func.now())
