ALTER TABLE `transaction_start`
    ADD COLUMN `charge_box_id` VARCHAR(255) NOT NULL AFTER `connector_pk`;