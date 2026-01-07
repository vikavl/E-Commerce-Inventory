CREATE TABLE product (
                         id             VARCHAR(20)  NOT NULL,
                         sku            VARCHAR(255),
                         name           VARCHAR(500),
                         description    TEXT,
                         price          DECIMAL(10,2),
                         image_url      VARCHAR(255),
                         stock_quantity INT,
                         created_at     DATETIME,
                         updated_at     DATETIME,
                         CONSTRAINT pk_product PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `order` (
                        id            BIGINT NOT NULL AUTO_INCREMENT,
                        customer_id   VARCHAR(255),
                        total_amount  DECIMAL(10,2),
                        status        SMALLINT,
                        created_at    DATETIME,
                        CONSTRAINT pk_order PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE order_item (
                            id          BIGINT NOT NULL AUTO_INCREMENT,
                            product_id  VARCHAR(20),
                            order_id    BIGINT,
                            quantity    INT,
                            unit_price  DECIMAL(10,2),
                            CONSTRAINT pk_order_item PRIMARY KEY (id),
                            CONSTRAINT fk_order_item_on_order
                                FOREIGN KEY (order_id) REFERENCES `order` (id),
                            CONSTRAINT fk_order_item_on_product
                                FOREIGN KEY (product_id) REFERENCES product (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
