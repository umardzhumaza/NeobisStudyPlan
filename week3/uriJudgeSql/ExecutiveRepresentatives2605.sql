SELECT PRODUCTS.NAME, PROVIDERS.NAME
FROM PRODUCTS
JOIN PROVIDERS ON PRODUCTS.ID_PROVIDERS = PROVIDERS.ID
JOIN CATEGORIES ON PRODUCTS.ID_CATEGORIES = CATEGORIES.ID
WHERE CATEGORIES.ID = 6;