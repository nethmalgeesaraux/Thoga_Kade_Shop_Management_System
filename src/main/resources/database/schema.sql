CREATE DATABASE IF NOT EXISTS thogakade;
USE thogakade;

CREATE TABLE customer (
    title VARCHAR(10),
    cus_id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    dob DATE,
    salary DECIMAL(10,2),
    city VARCHAR(50),
    province VARCHAR(50),
    postal_code VARCHAR(10),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE employee (
    emp_id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    nic VARCHAR(15) UNIQUE,
    dob DATE,
    position VARCHAR(50),
    salary DECIMAL(10,2),
    contact_number VARCHAR(15),
    address VARCHAR(255),
    join_date DATE,
    status VARCHAR(20) DEFAULT 'Active',
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE item (
    item_code VARCHAR(10) PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    category VARCHAR(50),
    qty_on_hand INT DEFAULT 0,
    unit_price DECIMAL(10,2),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE supplier (
    supplier_id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    company VARCHAR(100),
    address VARCHAR(255),
    city VARCHAR(50),
    province VARCHAR(50),
    postal_code VARCHAR(10),
    email VARCHAR(100),
    contact VARCHAR(15),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);