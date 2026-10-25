-- Electricity Billing System Database Schema
-- Database: Bill_system

CREATE DATABASE IF NOT EXISTS Bill_system;
USE Bill_system;

-- 1. Table for user logins and registration
CREATE TABLE IF NOT EXISTS Signup (
    meter_no VARCHAR(50),
    username VARCHAR(50),
    name VARCHAR(100),
    password VARCHAR(50),
    usertype VARCHAR(50)
);

-- 2. Table for storing registered customer details
CREATE TABLE IF NOT EXISTS new_customer (
    name VARCHAR(100),
    meter_no VARCHAR(50) PRIMARY KEY,
    address VARCHAR(255),
    city VARCHAR(50),
    state VARCHAR(50),
    email VARCHAR(100),
    phone_no VARCHAR(20)
);

-- 3. Table for meter specifications
CREATE TABLE IF NOT EXISTS meter_info (
    meter_no VARCHAR(50),
    meter_location VARCHAR(50),
    meter_type VARCHAR(50),
    phase_code VARCHAR(50),
    bill_type VARCHAR(50),
    Days VARCHAR(50)
);

-- 4. Table for tax structure and tariff rates
CREATE TABLE IF NOT EXISTS tax (
    cost_per_unit VARCHAR(50),
    meter_rent VARCHAR(50),
    service_charge VARCHAR(50),
    service_tax VARCHAR(50),
    swacch_bharat VARCHAR(50),
    fixed_tax VARCHAR(50)
);

-- Insert standard baseline rates
INSERT INTO tax (cost_per_unit, meter_rent, service_charge, service_tax, swacch_bharat, fixed_tax)
VALUES ('9', '47', '22', '57', '6', '18');

-- 5. Table for generated bills and payment records
CREATE TABLE IF NOT EXISTS bill (
    meter_no VARCHAR(50),
    month VARCHAR(50),
    unit VARCHAR(50),
    total_bill VARCHAR(50),
    status VARCHAR(50)
);
