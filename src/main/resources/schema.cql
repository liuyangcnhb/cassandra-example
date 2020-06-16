
CREATE TABLE IF NOT EXISTS pl.product_info(
    proj_code text,
    prod_batch_code text,
    win_head text,
    win_tail text,
    win_message text,
    primary key((proj_code, prod_batch_code, win_head), win_tail)
);
