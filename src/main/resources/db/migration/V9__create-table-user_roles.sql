create table user_roles(
    id bigint auto_increment primary key,
    user_id bigint,
    role_id bigint,

                           constraint fk_user_roles_user_id foreign key(user_id) references users(id),
                           constraint fk_user_roles_role_id foreign key(role_id) references roles(id)
);