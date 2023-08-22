CREATE TABLE public.clients (
	id bigserial NOT NULL,
	pin_hash varchar NOT NULL,
	first_name varchar NOT NULL,
	last_name varchar NOT NULL,
	father_name varchar NULL,
	CONSTRAINT clients_pk PRIMARY KEY (id),
	CONSTRAINT clients_un UNIQUE (id)
);


CREATE TABLE public.bank_accounts (
	id uuid NOT NULL,
	client_id int8 NOT NULL,
	balance numeric NOT NULL,
	CONSTRAINT bank_accounts_un UNIQUE (id)
);

ALTER TABLE public.bank_accounts ADD CONSTRAINT bank_accounts_fk FOREIGN KEY (client_id) REFERENCES public.clients(id);


CREATE TABLE public.transactions (
	account_number_from uuid NULL,
	account_number_to uuid NULL,
	amount numeric NOT NULL,
	created_at timestamp NOT NULL,
	id bigserial NOT NULL
);


ALTER TABLE public.transactions ADD CONSTRAINT transactions_fk FOREIGN KEY (account_number_from) REFERENCES public.bank_accounts(id);
ALTER TABLE public.transactions ADD CONSTRAINT transactions_fk_1 FOREIGN KEY (account_number_to) REFERENCES public.bank_accounts(id);
