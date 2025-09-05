#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
EV Charging CSMS Platform - API Router
电动汽车充电桩管理系统 - API路由
"""

from fastapi import APIRouter
from app.api.v1.endpoints import auth, users, charging_stations, payments, analytics

api_router = APIRouter()

# Authentication related routes
api_router.include_router(auth.router, prefix="/auth", tags=["Authentication"])

# User related routes
api_router.include_router(users.router, prefix="/users", tags=["User Management"])

# Charging station related routes
api_router.include_router(charging_stations.router, prefix="/charging-stations", tags=["Charging Station Management"])

# Payment related routes
api_router.include_router(payments.router, prefix="/payments", tags=["Payment Management"])

# Analytics related routes
api_router.include_router(analytics.router, prefix="/analytics", tags=["Data Analytics"])
