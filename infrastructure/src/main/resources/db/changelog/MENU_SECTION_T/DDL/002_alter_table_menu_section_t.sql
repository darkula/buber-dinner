--liquibase formatted sql
--changeset darkula:alter-table-menu-section-t

ALTER TABLE MENU_SECTION_T ADD UNIQUE(MENU_SECTION_ID);
ALTER TABLE MENU_SECTION_T ADD UNIQUE(MENU_ID);
ALTER TABLE MENU_SECTION_T ADD FOREIGN KEY (MENU_ID) REFERENCES MENU_T(ID);