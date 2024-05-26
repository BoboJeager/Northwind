USE northwind;

CREATE TABLE IF NOT EXISTS product (
    product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    supplier_id INT,
    category_id INT,
    quantity_per_unit VARCHAR(255),
    unit_price DOUBLE,
    units_in_stock INT,
    units_on_order INT,
    reorder_level INT,
    discontinued BOOLEAN
);

INSERT INTO product (product_name, supplier_id, category_id, quantity_per_unit, unit_price, units_in_stock, units_on_order, reorder_level, discontinued)
VALUES
    ('Product1', 1, 1, '10 boxes', 20.5, 100, 50, 10, false),
    ('Product2', 2, 1, '20 boxes', 30.0, 200, 20, 5, false),
    ('Product3', 3, 2, '15 boxes', 25.0, 150, 30, 8, true);

CREATE TABLE IF NOT EXISTS customer (
    customer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    company_name VARCHAR(255) NOT NULL,
    contact_title VARCHAR(255),
    address VARCHAR(255),
    city VARCHAR(255),
    region VARCHAR(255),
    postal_code VARCHAR(255),
    country VARCHAR(255),
    phone VARCHAR(255),
    fax VARCHAR(255)
);

-- Insert sample data into Customers table
INSERT INTO customer (company_name, contact_name, contact_title, address, city, region, postal_code, country, phone, fax)
VALUES
    ('Company1', 'Contact1', 'Title1', 'Address1', 'City1', 'Region1', 'PostalCode1', 'Country1', 'Phone1', 'Fax1'),
    ('Company2', 'Contact2', 'Title2', 'Address2', 'City2', 'Region2', 'PostalCode2', 'Country2', 'Phone2', 'Fax2'),
    ('Company3', 'Contact3', 'Title3', 'Address3', 'City3', 'Region3', 'PostalCode3', 'Country3', 'Phone3', 'Fax3');

