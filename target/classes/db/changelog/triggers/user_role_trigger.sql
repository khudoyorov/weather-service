insert into authorities(name) values('USER'),('ADMIN');

CREATE FUNCTION default_users_role()
    RETURNS TRIGGER
AS
    $$
BEGIN
   insert into roles(user_id, authority_id) values(NEW.id, 1);
RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER user_added_trigger
    AFTER INSERT
    ON users
    FOR EACH ROW
    EXECUTE PROCEDURE default_users_role();
