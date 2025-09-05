#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
EV Charging CSMS Platform - Charging Station Model
电动汽车充电桩管理系统 - 充电桩模型
"""

from sqlalchemy import Column, Integer, String, Boolean, DateTime, Float, Text, JSON
from sqlalchemy.sql import func
from sqlalchemy.orm import relationship
from app.core.database import Base


class ChargingStation(Base):
    """充电桩模型"""
    __tablename__ = "charging_stations"
    
    id = Column(Integer, primary_key=True, index=True)
    station_id = Column(String(50), unique=True, index=True, nullable=False)
    name = Column(String(100), nullable=False)
    description = Column(Text)
    
    # 位置信息
    address = Column(String(200), nullable=False)
    province = Column(String(50))
    city = Column(String(50))
    district = Column(String(50))
    latitude = Column(Float, nullable=False)
    longitude = Column(Float, nullable=False)
    
    # 运营商信息
    operator_id = Column(Integer, nullable=False, index=True)
    operator_name = Column(String(100))
    
    # 充电桩状态
    status = Column(String(20), default="offline")  # online, offline, maintenance, fault
    is_public = Column(Boolean, default=True)
    is_available = Column(Boolean, default=True)
    
    # 充电桩规格
    total_connectors = Column(Integer, default=0)
    available_connectors = Column(Integer, default=0)
    max_power = Column(Float)  # 最大功率(kW)
    connector_types = Column(JSON)  # 连接器类型列表
    
    # 价格信息
    price_per_kwh = Column(Float)  # 每度电价格
    price_per_minute = Column(Float)  # 每分钟价格
    parking_fee = Column(Float, default=0.0)  # 停车费
    
    # 服务时间
    service_hours = Column(JSON)  # 服务时间配置
    is_24_hours = Column(Boolean, default=False)
    
    # 设施信息
    amenities = Column(JSON)  # 设施列表
    payment_methods = Column(JSON)  # 支付方式
    
    # 时间戳
    created_at = Column(DateTime, default=func.now())
    updated_at = Column(DateTime, default=func.now(), onupdate=func.now())
    last_heartbeat = Column(DateTime)
    
    # 关联关系
    connectors = relationship("ChargingConnector", back_populates="station")
    charging_sessions = relationship("ChargingSession", back_populates="station")


class ChargingConnector(Base):
    """充电连接器模型"""
    __tablename__ = "charging_connectors"
    
    id = Column(Integer, primary_key=True, index=True)
    station_id = Column(Integer, nullable=False, index=True)
    connector_id = Column(String(50), nullable=False)
    
    # 连接器信息
    connector_type = Column(String(20), nullable=False)  # AC, DC
    power_rating = Column(Float, nullable=False)  # 功率等级(kW)
    voltage = Column(Float)  # 电压(V)
    current = Column(Float)  # 电流(A)
    
    # 状态信息
    status = Column(String(20), default="available")  # available, occupied, fault, maintenance
    is_online = Column(Boolean, default=True)
    last_status_update = Column(DateTime)
    
    # 时间戳
    created_at = Column(DateTime, default=func.now())
    updated_at = Column(DateTime, default=func.now(), onupdate=func.now())
    
    # 关联关系
    station = relationship("ChargingStation", back_populates="connectors")
    charging_sessions = relationship("ChargingSession", back_populates="connector")


class ChargingSession(Base):
    """充电会话模型"""
    __tablename__ = "charging_sessions"
    
    id = Column(Integer, primary_key=True, index=True)
    session_id = Column(String(50), unique=True, index=True, nullable=False)
    
    # 关联信息
    user_id = Column(Integer, nullable=False, index=True)
    station_id = Column(Integer, nullable=False, index=True)
    connector_id = Column(Integer, nullable=False, index=True)
    vehicle_id = Column(Integer, index=True)
    
    # 充电信息
    start_time = Column(DateTime, nullable=False)
    end_time = Column(DateTime)
    duration = Column(Integer)  # 充电时长(秒)
    
    # 电量信息
    start_soc = Column(Float)  # 开始SOC(%)
    end_soc = Column(Float)  # 结束SOC(%)
    energy_delivered = Column(Float)  # 充电电量(kWh)
    max_power = Column(Float)  # 最大功率(kW)
    average_power = Column(Float)  # 平均功率(kW)
    
    # 费用信息
    energy_cost = Column(Float, default=0.0)  # 电费
    service_fee = Column(Float, default=0.0)  # 服务费
    parking_fee = Column(Float, default=0.0)  # 停车费
    total_cost = Column(Float, default=0.0)  # 总费用
    
    # 状态信息
    status = Column(String(20), default="active")  # active, completed, cancelled, fault
    stop_reason = Column(String(50))  # 停止原因
    
    # 时间戳
    created_at = Column(DateTime, default=func.now())
    updated_at = Column(DateTime, default=func.now(), onupdate=func.now())
    
    # 关联关系
    user = relationship("User", back_populates="charging_sessions")
    station = relationship("ChargingStation", back_populates="charging_sessions")
    connector = relationship("ChargingConnector", back_populates="charging_sessions")
    vehicle = relationship("Vehicle", back_populates="charging_sessions")
    payments = relationship("Payment", back_populates="charging_session")
