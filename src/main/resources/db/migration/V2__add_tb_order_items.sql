create table order_items (
    id                      bigserial primary key,
    quantity                int,
    price_per_product       int,
    price                   int,
    product_id              bigint
);