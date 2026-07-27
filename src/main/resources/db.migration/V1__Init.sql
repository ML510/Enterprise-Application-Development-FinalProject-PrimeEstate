CREATE TABLE property (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    state VARCHAR(100),
    price DECIMAL(19, 2),
    max_bed_count INTEGER,
    min_bed_count INTEGER,
    url VARCHAR(300),
    sqft INTEGER,
    dsc TEXT,
    status VARCHAR(255),
    added_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE leads(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(20),
    last_contact TIMESTAMP,
    stage VARCHAR(100),
    profile_pic VARCHAR(512)
);

