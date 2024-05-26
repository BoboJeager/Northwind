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
    contact_name VARCHAR(255),
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

CREATE TABLE IF NOT EXISTS orders (
    order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT,
    employee_id BIGINT,
    order_date DATE,
    required_date DATE,
    shipped_date DATE,
    ship_via BIGINT,
    freight DOUBLE,
    ship_name VARCHAR(255),
    ship_address VARCHAR(255),
    ship_city VARCHAR(255),
    ship_region VARCHAR(255),
    ship_postal_code VARCHAR(255),
    ship_country VARCHAR(255),
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

-- Insert sample data into Orders table
INSERT INTO orders (customer_id, employee_id, order_date, required_date, shipped_date, ship_via, freight, ship_name, ship_address, ship_city, ship_region, ship_postal_code, ship_country)
VALUES
    (1, 1, '2024-01-01', '2024-01-05', '2024-01-03', 1, 15.5, 'ShipName1', 'ShipAddress1', 'ShipCity1', 'ShipRegion1', 'ShipPostalCode1', 'ShipCountry1'),
    (2, 2, '2024-02-01', '2024-02-05', '2024-02-03', 2, 20.0, 'ShipName2', 'ShipAddress2', 'ShipCity2', 'ShipRegion2', 'ShipPostalCode2', 'ShipCountry2'),
    (3, 3, '2024-03-01', '2024-03-05', '2024-03-03', 3, 25.0, 'ShipName3', 'ShipAddress3', 'ShipCity3', 'ShipRegion3', 'ShipPostalCode3', 'ShipCountry3');

-- Create OrderDetails table
CREATE TABLE IF NOT EXISTS order_detail (
    order_detail_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT,
    product_id BIGINT,
    unit_price DOUBLE,
    quantity INT,
    discount DOUBLE,
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);

INSERT INTO order_detail (order_id, product_id, unit_price, quantity, discount)
VALUES
    (1, 1, 20.5, 5, 0.1),
    (2, 2, 30.0, 10, 0.2),
    (3, 3, 25.0, 7, 0.15);
