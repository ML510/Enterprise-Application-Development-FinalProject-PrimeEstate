CREATE TABLE subscribers (
 id INTEGER PRIMARY KEY AUTO_INCREMENT,
 email VARCHAR(255) NOT NULL UNIQUE,
 status VARCHAR(20) DEFAULT 'ACTIVE', -- ACTIVE, UNSUBSCRIBED, BOUNCED
 subscribed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Optional: UNIQUE(email) already creates an index in MySQL, so this is usually redundant
-- CREATE INDEX idx_subscribers_email ON subscribers(email);

CREATE INDEX idx_subscribers_status ON subscribers(status);
