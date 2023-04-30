--liquibase formatted sql
--changeset darkula:alter-table-menu-t

ALTER TABLE MENU_T ADD UNIQUE(ID);