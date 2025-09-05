#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
EV Charging CSMS Platform - Configuration
电动汽车充电桩管理系统 - 配置管理
"""

import os
from typing import List, Optional
from pydantic import BaseSettings, validator


class Settings(BaseSettings):
    """Application configuration"""
    
    # Basic application configuration
    APP_NAME: str = "Xcharge AI CSMS Platform"
    VERSION: str = "1.0.0"
    DEBUG: bool = False
    HOST: str = "0.0.0.0"
    PORT: int = 8000
    
    # Database configuration
    DATABASE_URL: str = "postgresql://user:password@localhost:5432/xcharge_ai_csms"
    REDIS_URL: str = "redis://localhost:6379/0"
    
    # Security configuration
    SECRET_KEY: str = "your-secret-key-here"
    ALGORITHM: str = "HS256"
    ACCESS_TOKEN_EXPIRE_MINUTES: int = 30
    REFRESH_TOKEN_EXPIRE_DAYS: int = 7
    
    # CORS configuration
    BACKEND_CORS_ORIGINS: List[str] = [
        "http://localhost:3000",
        "http://localhost:3001",
        "http://localhost:8080",
    ]
    
    # Payment configuration
    ALIPAY_APP_ID: Optional[str] = None
    ALIPAY_PRIVATE_KEY: Optional[str] = None
    ALIPAY_PUBLIC_KEY: Optional[str] = None
    
    WECHAT_APP_ID: Optional[str] = None
    WECHAT_MCH_ID: Optional[str] = None
    WECHAT_API_KEY: Optional[str] = None
    
    # Map service configuration
    AMAP_API_KEY: Optional[str] = None
    BAIDU_MAP_API_KEY: Optional[str] = None
    
    # SMS service configuration
    SMS_ACCESS_KEY_ID: Optional[str] = None
    SMS_ACCESS_KEY_SECRET: Optional[str] = None
    
    # Email service configuration
    SMTP_HOST: Optional[str] = None
    SMTP_PORT: int = 587
    SMTP_USERNAME: Optional[str] = None
    SMTP_PASSWORD: Optional[str] = None
    
    # File storage configuration
    UPLOAD_DIR: str = "uploads"
    MAX_FILE_SIZE: int = 10 * 1024 * 1024  # 10MB
    
    # Charging station configuration
    CHARGING_STATION_TIMEOUT: int = 30  # Charging station timeout (seconds)
    CHARGING_SESSION_TIMEOUT: int = 3600  # Charging session timeout (seconds)
    
    # Monitoring configuration
    PROMETHEUS_ENABLED: bool = True
    LOG_LEVEL: str = "INFO"
    
    @validator("BACKEND_CORS_ORIGINS", pre=True)
    def assemble_cors_origins(cls, v):
        if isinstance(v, str) and not v.startswith("["):
            return [i.strip() for i in v.split(",")]
        elif isinstance(v, (list, str)):
            return v
        raise ValueError(v)
    
    class Config:
        env_file = ".env"
        case_sensitive = True


# 创建全局配置实例
settings = Settings()
