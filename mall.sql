-- MEMBER
CREATE TABLE member
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(100)        NOT NULL,
    email      VARCHAR(100) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    nickname   VARCHAR(100)        NOT NULL,
    phone      VARCHAR(20)         NOT NULL,
    gender     VARCHAR(10)         NOT NULL,
    is_deleted BOOLEAN DEFAULT FALSE,
    deleted_at DATETIME,
    created_at DATETIME,
    updated_at DATETIME
);

-- DELIVERY_INFO
CREATE TABLE delivery_info
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id   BIGINT       NOT NULL,
    address     VARCHAR(255) NOT NULL,
    requirement TEXT,
    is_deleted  BOOLEAN DEFAULT FALSE,
    deleted_at  DATETIME,
    created_at  DATETIME,
    updated_at  DATETIME,
    FOREIGN KEY (member_id) REFERENCES member (id)
);

-- PRODUCT
CREATE TABLE product
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    price       INT          NOT NULL,
    stock       INT          NOT NULL,
    description TEXT         NOT NULL,
    category    BIGINT       NOT NULL,
    is_deleted  BOOLEAN DEFAULT FALSE,
    deleted_at  DATETIME,
    created_at  DATETIME,
    updated_at  DATETIME
);

-- ORDER
CREATE TABLE `order`
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id   BIGINT       NOT NULL,
    total_price INT          NOT NULL,
    requirement TEXT,
    address     VARCHAR(255) NOT NULL,
    is_deleted  BOOLEAN DEFAULT FALSE,
    deleted_at  DATETIME,
    created_at  DATETIME,
    updated_at  DATETIME,
    FOREIGN KEY (member_id) REFERENCES member (id)
);

-- ORDER_DETAIL
CREATE TABLE order_detail
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id   BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    price      INT    NOT NULL,
    cnt        INT    NOT NULL,
    is_deleted BOOLEAN DEFAULT FALSE,
    deleted_at DATETIME,
    created_at DATETIME,
    updated_at DATETIME,
    FOREIGN KEY (order_id) REFERENCES `order` (id),
    FOREIGN KEY (product_id) REFERENCES product (id)
);
