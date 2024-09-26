-- Procedure for insert tables
CREATE procedure if not exists initialize_superuser()
begin
	declare tenantId BigInt;
	declare propertyId BigInt;
	declare userId BigInt;
	declare roleId BigInt;
	declare propertyUserId BigInt;
	declare userRoleId BigInt;
	declare clientId BigInt;
	
	-- Generate the snowflakeIds
	START TRANSACTION;
	call GenerateSnowflakeID(tenantId);
	call GenerateSnowflakeID(propertyId);
	call GenerateSnowflakeID(userId);
	call GenerateSnowflakeID(roleId);
	call GenerateSnowflakeID(propertyUserId);
	call GenerateSnowflakeID(userRoleId);
	call GenerateSnowflakeID(clientId); 
	
	-- Insert tables
	insert into `group` (id,name,code,contact_name,contact_phone,contact_email,multi_property,number_of_property,created_by,created_time,
	updated_by,updated_time) 
	values(tenantId,'admin owner','admin_owner','admin',null,null,0,null,'admin',NOW(),null,null);

	insert into property (id,name,code,contact_name,contact_phone,contact_email,tenant_id,city,province,country,group_flag,description,
	created_by,created_time,updated_by,updated_time) 
	values(propertyId,'admin owner','admin_owner',null,null,null,tenantId,null,null,null,1,null,'admin',NOW(),null,null);

	insert into `role` (id,name,code,created_by,created_time,updated_by,updated_time) 
	values(roleId,'admin','admin','Admin',NOW(),null,null);

	insert into `user` (id,tenant_id,property_id,username,password,mobile_phone,email,role_id,created_by,created_time,updated_by,updated_time)
	values(userId,tenantId,propertyId,'admin','$2y$10$qiqMSIiM/.8iUiWVIjTMN.1CoZUzzeDk5CiHF.fY8Lh8j3YV0B0va','15910770789','772844248@qq.com',
	roleId,'admin',NOW(),null,null);

	insert into property_user (id,tenant_id,property_id,user_id,created_by,created_time,updated_by,updated_time)
	values(propertyUserId,tenantId,propertyId,userId,'admin',NOW(),null,null);

	insert into user_role (id,user_id,role_id,created_by,created_time,updated_by,updated_time) 
	values(userRoleId,userId,roleId,'admin',NOW(),null,null);

	insert into `client` (id,tenant_id,client_secret,access_token_expires,refresh_token_lifespan,origins,description,created_by,created_time,updated_by,updated_time)
	values(clientId,tenantId,SHA2(UUID(), 256),60,4320,'*','This is a client configuration for superaccount that belong to admin',
			'admin',NOW(),null,null);
	COMMIT;
end;

-- Call procedure
call initialize_superuser(); 

drop procedure if exists initialize_superuser;


-- Create procedure for generate the snowflakeId
CREATE PROCEDURE GenerateSnowflakeID(OUT snowflake_id_result BigInt)
BEGIN
    DECLARE epoch BIGINT DEFAULT 1420041600000;  -- Custom epoch (e.g., Jan 1, 2023)
    DECLARE current_ts BIGINT DEFAULT UNIX_TIMESTAMP(NOW(3)) * 1000; -- Current time in ms
    DECLARE machine_id BIGINT DEFAULT 1;  -- Set machine ID (up to 10 bits)
    DECLARE datacenter_id BIGINT DEFAULT 1;  -- Set datacenter ID (up to 5 bits)
    DECLARE sequence BIGINT DEFAULT 0;  -- Sequence (up to 12 bits)

    -- Shift the components to build the Snowflake ID
    DECLARE snowflake_id BIGINT DEFAULT ((current_ts - epoch) << 22) |
                                      (datacenter_id << 17) |
                                      (machine_id << 12) |
                                      sequence;
	-- set the output variable
	set snowflake_id_result = snowflake_id;
    -- Output the generated Snowflake ID
    -- SELECT snowflake_id;
   	
END

drop procedure if exists GenerateSnowflakeID;


-- Test GenerateSnowflakeId() procedure
set @snowflakeId=0;

call GenerateSnowflakeID(@snowflakeId);

select @snowflakeId;


