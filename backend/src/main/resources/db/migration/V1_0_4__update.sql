-- reconstruct `transaction` as a view for database changes to be transparent to java app
-- select LATEST stop transaction events when joining
CREATE OR REPLACE VIEW `transaction` AS
 SELECT
  tx1.transaction_pk, tx1.connector_pk, tx1.charge_box_id, tx1.id_tag, tx1.event_timestamp as 'start_event_timestamp', tx1.start_timestamp, tx1.start_value,
  tx2.event_actor as 'stop_event_actor', tx2.event_timestamp as 'stop_event_timestamp', tx2.stop_timestamp, tx2.stop_value, tx2.stop_reason
  FROM transaction_start tx1
  LEFT JOIN (
    SELECT s1.*
    FROM transaction_stop s1
    WHERE s1.event_timestamp = (SELECT MAX(event_timestamp) FROM transaction_stop s2 WHERE s1.transaction_pk = s2.transaction_pk)
    GROUP BY s1.transaction_pk, s1.event_timestamp) tx2
  ON tx1.transaction_pk = tx2.transaction_pk;
