SELECT PRODUCTS.ID, PRODUCTS.NAME FROM PRODUCTS
JOIN CATEGORIES ON PRODUCTS.ID_CATEGORIES = CATEGORIES.ID WHERE CATEGORIES.NAME LIKE ('super%');