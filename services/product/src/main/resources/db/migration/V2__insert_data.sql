-- Insert into category table
INSERT INTO category (id, description, name)
VALUES
  (nextval('category_seq'), 'Category for electronics products', 'Electronics'),
  (nextval('category_seq'), 'Category for clothing products', 'Clothing'),
  (nextval('category_seq'), 'Category for home appliances', 'Home Appliances');

-- Insert into product table
INSERT INTO product (id, description, name, available_quantity, price, category_id)
VALUES
  (nextval('product_seq'), 'Smartphone with 6GB RAM and 128GB storage', 'Smartphone', 150, 699.99, (SELECT id FROM category WHERE name = 'Electronics')),
  (nextval('product_seq'), 'T-shirt made of cotton', 'T-shirt', 200, 19.99, (SELECT id FROM category WHERE name = 'Clothing')),
  (nextval('product_seq'), 'Refrigerator with 300L capacity', 'Refrigerator', 50, 499.99, (SELECT id FROM category WHERE name = 'Home Appliances')),
  (nextval('product_seq'), 'Wireless Bluetooth Headphones', 'Headphones', 75, 99.99, (SELECT id FROM category WHERE name = 'Electronics')),
  (nextval('product_seq'), 'Winter Jacket', 'Jacket', 40, 129.99, (SELECT id FROM category WHERE name = 'Clothing'));
