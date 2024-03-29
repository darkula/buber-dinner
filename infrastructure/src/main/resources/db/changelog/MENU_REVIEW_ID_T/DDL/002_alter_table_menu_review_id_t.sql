--liquibase formatted sql
--changeset darkula:alter-table-menu-review-id-t

ALTER TABLE MENU_REVIEW_ID_T ADD UNIQUE(MENU_REVIEW_ID);
ALTER TABLE MENU_REVIEW_ID_T ADD UNIQUE(MENU_ID);
ALTER TABLE MENU_REVIEW_ID_T ADD FOREIGN KEY (MENU_ID) REFERENCES MENU_T(ID);