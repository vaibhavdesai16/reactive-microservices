CREATE TABLE IF NOT EXISTS consumption (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
	customer_id int8 NOT NULL,
	"timestamp" timestamp NOT NULL,
	kw float8 NOT NULL
);
ALTER TABLE consumption ADD CONSTRAINT consumption_fk FOREIGN KEY (customer_id) REFERENCES customer(customer_id);


