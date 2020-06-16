CREATE TABLE IF NOT EXISTS pl.lottery(
    proj_code text,
    prod_batch_code text,
    book_no text,
    action_sequence int,
    action_status int,
    action_date_time timestamp,
    wh_no text,
    relation_wh_no text,
    lock_flag int,
    lock_user_no text,
    lock_date_time timestamp,
    logistics_no text,
    primary key((proj_code, prod_batch_code, book_no), action_sequence, action_status)
);

CREATE TABLE IF NOT EXISTS pl.win_info(
    proj_code text,
    prod_batch_code text,
    win_head text,
    win_tail text,
    win_message text,
    primary key((proj_code, prod_batch_code, win_head), win_tail)
);
