#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
EV Charging CSMS Platform - Main Application
电动汽车充电桩管理系统 - 主应用
"""

from fastapi import FastAPI, HTTPException, Depends
from fastapi.middleware.cors import CORSMiddleware
from fastapi.middleware.trustedhost import TrustedHostMiddleware
from fastapi.security import HTTPBearer
import uvicorn
import os
from contextlib import asynccontextmanager

from app.core.config import settings
from app.core.database import init_db
from app.api.v1.api import api_router
from app.core.middleware import add_middleware


@asynccontextmanager
async def lifespan(app: FastAPI):
    """Application lifecycle management"""
    # Startup execution
    print("🚀 Starting Xcharge AI CSMS Platform...")
    await init_db()
    print("✅ Database initialization completed")
    yield
    # Shutdown execution
    print("🛑 Shutting down Xcharge AI CSMS Platform...")


# Create FastAPI application
app = FastAPI(
    title="Xcharge AI CSMS Platform",
    description="Intelligent Electric Vehicle Charging Station Management System",
    version="1.0.0",
    docs_url="/docs",
    redoc_url="/redoc",
    lifespan=lifespan
)

# Add middleware
add_middleware(app)

# Add API routes
app.include_router(api_router, prefix="/api/v1")


@app.get("/")
async def root():
    """Root endpoint"""
    return {
        "message": "Xcharge AI CSMS Platform",
        "version": "1.0.0",
        "status": "running",
        "docs": "/docs"
    }


@app.get("/health")
async def health_check():
    """Health check endpoint"""
    return {
        "status": "healthy",
        "service": "xcharge-ai-csms",
        "version": "1.0.0"
    }


if __name__ == "__main__":
    uvicorn.run(
        "main:app",
        host=settings.HOST,
        port=settings.PORT,
        reload=settings.DEBUG,
        log_level="info"
    )
