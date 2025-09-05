#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
EV Charging CSMS Platform - Payment Model
电动汽车充电桩管理系统 - 支付模型
"""

from sqlalchemy import Column, Integer, String, Boolean, DateTime, Float, Text, JSON
from sqlalchemy.sql import func
from sqlalchemy.orm import relationship
from app.core.database import Base


class Payment(Base):
    """支付记录模型"""
    __tablename__ = "payments"
    
    id = Column(Integer, primary_key=True, index=True)
    payment_id = Column(String(50), unique=True, index=True, nullable=False)
    
    # 关联信息
    user_id = Column(Integer, nullable=False, index=True)
    charging_session_id = Column(Integer, index=True)
    
    # 支付信息
    amount = Column(Float, nullable=False)  # 支付金额
    currency = Column(String(3), default="CNY")  # 货币类型
    payment_method = Column(String(20), nullable=False)  # 支付方式
    payment_status = Column(String(20), default="pending")  # 支付状态
    
    # 第三方支付信息
    third_party_payment_id = Column(String(100))  # 第三方支付ID
    third_party_transaction_id = Column(String(100))  # 第三方交易ID
    
    # 支付详情
    payment_details = Column(JSON)  # 支付详情JSON
    failure_reason = Column(Text)  # 失败原因
    
    # 时间戳
    created_at = Column(DateTime, default=func.now())
    updated_at = Column(DateTime, default=func.now(), onupdate=func.now())
    paid_at = Column(DateTime)  # 支付完成时间
    
    # 关联关系
    user = relationship("User", back_populates="payments")
    charging_session = relationship("ChargingSession", back_populates="payments")


class Refund(Base):
    """退款记录模型"""
    __tablename__ = "refunds"
    
    id = Column(Integer, primary_key=True, index=True)
    refund_id = Column(String(50), unique=True, index=True, nullable=False)
    payment_id = Column(Integer, nullable=False, index=True)
    
    # 退款信息
    amount = Column(Float, nullable=False)  # 退款金额
    reason = Column(Text)  # 退款原因
    status = Column(String(20), default="pending")  # 退款状态
    
    # 第三方退款信息
    third_party_refund_id = Column(String(100))  # 第三方退款ID
    
    # 时间戳
    created_at = Column(DateTime, default=func.now())
    updated_at = Column(DateTime, default=func.now(), onupdate=func.now())
    processed_at = Column(DateTime)  # 处理完成时间


class Invoice(Base):
    """发票模型"""
    __tablename__ = "invoices"
    
    id = Column(Integer, primary_key=True, index=True)
    invoice_id = Column(String(50), unique=True, index=True, nullable=False)
    payment_id = Column(Integer, nullable=False, index=True)
    
    # 发票信息
    invoice_type = Column(String(20), default="electronic")  # 发票类型
    invoice_title = Column(String(100))  # 发票抬头
    tax_number = Column(String(50))  # 税号
    email = Column(String(100))  # 邮箱
    
    # 发票状态
    status = Column(String(20), default="pending")  # 发票状态
    invoice_url = Column(String(255))  # 发票下载链接
    
    # 时间戳
    created_at = Column(DateTime, default=func.now())
    updated_at = Column(DateTime, default=func.now(), onupdate=func.now())
    issued_at = Column(DateTime)  # 开票时间


class Wallet(Base):
    """钱包模型"""
    __tablename__ = "wallets"
    
    id = Column(Integer, primary_key=True, index=True)
    user_id = Column(Integer, unique=True, index=True, nullable=False)
    
    # 余额信息
    balance = Column(Float, default=0.0)  # 余额
    frozen_balance = Column(Float, default=0.0)  # 冻结余额
    total_recharge = Column(Float, default=0.0)  # 总充值
    total_consumption = Column(Float, default=0.0)  # 总消费
    
    # 状态
    is_active = Column(Boolean, default=True)
    
    # 时间戳
    created_at = Column(DateTime, default=func.now())
    updated_at = Column(DateTime, default=func.now(), onupdate=func.now())


class WalletTransaction(Base):
    """钱包交易记录模型"""
    __tablename__ = "wallet_transactions"
    
    id = Column(Integer, primary_key=True, index=True)
    wallet_id = Column(Integer, nullable=False, index=True)
    
    # 交易信息
    transaction_type = Column(String(20), nullable=False)  # 交易类型
    amount = Column(Float, nullable=False)  # 交易金额
    balance_before = Column(Float, nullable=False)  # 交易前余额
    balance_after = Column(Float, nullable=False)  # 交易后余额
    
    # 关联信息
    related_payment_id = Column(Integer)  # 关联支付ID
    related_session_id = Column(Integer)  # 关联充电会话ID
    
    # 交易描述
    description = Column(Text)
    
    # 时间戳
    created_at = Column(DateTime, default=func.now())
