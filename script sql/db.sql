CREATE TYPE "order_status" AS ENUM (
  'processing',
  'delivering',
  'delivered'
);

CREATE TABLE "user" (
  "id" UUID PRIMARY KEY,
  "name" varchar,
  "surname" varchar,
  "address" varchar,
  "birthdate" date,
  "balance" double precision
);

CREATE TABLE "cart" (
  "id" uuid PRIMARY KEY,
  "user_id" uuid UNIQUE
);

CREATE TABLE "cart_product" (
  "cart_id" UUID,
  "product_id" UUID
);

CREATE TABLE "seller" (
  "id" UUID PRIMARY KEY,
  "full_name" varchar,
  "address" varchar
);

CREATE TABLE "product" (
  "id" UUID PRIMARY KEY,
  "name" varchar,
  "price" double precision,
  "stock" int,
  "description" text,
  "seller_id" UUID
);

CREATE TABLE "order" (
  "id" UUID PRIMARY KEY,
  "user_id" UUID NOT NULL,
  "status" order_status,
  "total" double precision,
  "created_at" timestamp
);

CREATE TABLE "order_product" (
  "order_id" UUID,
  "product_id" UUID,
  "quantity" int DEFAULT 1
);

CREATE TABLE "wishlist" (
  "id" UUID PRIMARY KEY,
  "name" varchar,
  "user_id" UUID NOT NULL,
  "created_at" timestamp
);

CREATE TABLE "wishlist_product" (
  "wishlist_id" UUID,
  "product_id" UUID
);

CREATE TABLE "review" (
  "id" UUID PRIMARY KEY,
  "opinion" text,
  "rating" int,
  "user_id" uuid,
  "product_id" uuid
);


ALTER TABLE "cart" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "cart_product" ADD FOREIGN KEY ("cart_id") REFERENCES "cart" ("id");

ALTER TABLE "cart_product" ADD FOREIGN KEY ("product_id") REFERENCES "product" ("id");

ALTER TABLE "product" ADD FOREIGN KEY ("seller_id") REFERENCES "seller" ("id");

ALTER TABLE "order" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "order_product" ADD FOREIGN KEY ("order_id") REFERENCES "order" ("id");

ALTER TABLE "order_product" ADD FOREIGN KEY ("product_id") REFERENCES "product" ("id");

ALTER TABLE "wishlist" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "wishlist_product" ADD FOREIGN KEY ("wishlist_id") REFERENCES "wishlist" ("id");

ALTER TABLE "wishlist_product" ADD FOREIGN KEY ("product_id") REFERENCES "product" ("id");

ALTER TABLE "review" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "review" ADD FOREIGN KEY ("product_id") REFERENCES "product" ("id");


alter table cart_product
    add quantity int default 1;
