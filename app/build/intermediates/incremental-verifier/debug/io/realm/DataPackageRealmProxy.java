package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.app.renhao.dietwallet.DataPackage;
import io.realm.RealmFieldType;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.ImplicitTransaction;
import io.realm.internal.LinkView;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Table;
import io.realm.internal.TableOrView;
import io.realm.internal.android.JsonUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataPackageRealmProxy extends DataPackage
    implements RealmObjectProxy {

    static final class DataPackageColumnInfo extends ColumnInfo {

        public final long snoIndex;
        public final long dateIndex;
        public final long moneyIndex;
        public final long calorieIndex;
        public final long foodnameIndex;

        DataPackageColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(5);
            this.snoIndex = getValidColumnIndex(path, table, "DataPackage", "sno");
            indicesMap.put("sno", this.snoIndex);

            this.dateIndex = getValidColumnIndex(path, table, "DataPackage", "date");
            indicesMap.put("date", this.dateIndex);

            this.moneyIndex = getValidColumnIndex(path, table, "DataPackage", "money");
            indicesMap.put("money", this.moneyIndex);

            this.calorieIndex = getValidColumnIndex(path, table, "DataPackage", "calorie");
            indicesMap.put("calorie", this.calorieIndex);

            this.foodnameIndex = getValidColumnIndex(path, table, "DataPackage", "foodname");
            indicesMap.put("foodname", this.foodnameIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final DataPackageColumnInfo columnInfo;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("sno");
        fieldNames.add("date");
        fieldNames.add("money");
        fieldNames.add("calorie");
        fieldNames.add("foodname");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    DataPackageRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (DataPackageColumnInfo) columnInfo;
    }

    @Override
    @SuppressWarnings("cast")
    public int getSno() {
        realm.checkIfValid();
        return (int) row.getLong(columnInfo.snoIndex);
    }

    @Override
    public void setSno(int value) {
        realm.checkIfValid();
        row.setLong(columnInfo.snoIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getDate() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.dateIndex);
    }

    @Override
    public void setDate(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.dateIndex);
            return;
        }
        row.setString(columnInfo.dateIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int getMoney() {
        realm.checkIfValid();
        return (int) row.getLong(columnInfo.moneyIndex);
    }

    @Override
    public void setMoney(int value) {
        realm.checkIfValid();
        row.setLong(columnInfo.moneyIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int getCalorie() {
        realm.checkIfValid();
        return (int) row.getLong(columnInfo.calorieIndex);
    }

    @Override
    public void setCalorie(int value) {
        realm.checkIfValid();
        row.setLong(columnInfo.calorieIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getFoodname() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.foodnameIndex);
    }

    @Override
    public void setFoodname(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.foodnameIndex);
            return;
        }
        row.setString(columnInfo.foodnameIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_DataPackage")) {
            Table table = transaction.getTable("class_DataPackage");
            table.addColumn(RealmFieldType.INTEGER, "sno", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "date", Table.NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "money", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.INTEGER, "calorie", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "foodname", Table.NULLABLE);
            table.addSearchIndex(table.getColumnIndex("sno"));
            table.setPrimaryKey("sno");
            return table;
        }
        return transaction.getTable("class_DataPackage");
    }

    public static DataPackageColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_DataPackage")) {
            Table table = transaction.getTable("class_DataPackage");
            if (table.getColumnCount() != 5) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 5 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 5; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final DataPackageColumnInfo columnInfo = new DataPackageColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("sno")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'sno' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("sno") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'int' for field 'sno' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.snoIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'sno' does support null values in the existing Realm file. Use corresponding boxed type for field 'sno' or migrate using io.realm.internal.Table.convertColumnToNotNullable().");
            }
            if (table.getPrimaryKey() != table.getColumnIndex("sno")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Primary key not defined for field 'sno' in existing Realm file. Add @PrimaryKey.");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("sno"))) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Index not defined for field 'sno' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
            }
            if (!columnTypes.containsKey("date")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'date' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("date") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'date' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.dateIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'date' is required. Either set @Required to field 'date' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("money")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'money' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("money") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'int' for field 'money' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.moneyIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'money' does support null values in the existing Realm file. Use corresponding boxed type for field 'money' or migrate using io.realm.internal.Table.convertColumnToNotNullable().");
            }
            if (!columnTypes.containsKey("calorie")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'calorie' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("calorie") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'int' for field 'calorie' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.calorieIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'calorie' does support null values in the existing Realm file. Use corresponding boxed type for field 'calorie' or migrate using io.realm.internal.Table.convertColumnToNotNullable().");
            }
            if (!columnTypes.containsKey("foodname")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'foodname' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("foodname") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'foodname' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.foodnameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'foodname' is required. Either set @Required to field 'foodname' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The DataPackage class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_DataPackage";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static DataPackage createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        DataPackage obj = null;
        if (update) {
            Table table = realm.getTable(DataPackage.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (!json.isNull("sno")) {
                long rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("sno"));
                if (rowIndex != TableOrView.NO_MATCH) {
                    obj = new DataPackageRealmProxy(realm.schema.getColumnInfo(DataPackage.class));
                    obj.realm = realm;
                    obj.row = table.getUncheckedRow(rowIndex);
                }
            }
        }
        if (obj == null) {
            if (json.has("sno")) {
                if (json.isNull("sno")) {
                    obj = realm.createObject(DataPackage.class, null);
                } else {
                    obj = realm.createObject(DataPackage.class, json.getInt("sno"));
                }
            } else {
                obj = realm.createObject(DataPackage.class);
            }
        }
        if (json.has("sno")) {
            if (json.isNull("sno")) {
                throw new IllegalArgumentException("Trying to set non-nullable field sno to null.");
            } else {
                obj.setSno((int) json.getInt("sno"));
            }
        }
        if (json.has("date")) {
            if (json.isNull("date")) {
                obj.setDate(null);
            } else {
                obj.setDate((String) json.getString("date"));
            }
        }
        if (json.has("money")) {
            if (json.isNull("money")) {
                throw new IllegalArgumentException("Trying to set non-nullable field money to null.");
            } else {
                obj.setMoney((int) json.getInt("money"));
            }
        }
        if (json.has("calorie")) {
            if (json.isNull("calorie")) {
                throw new IllegalArgumentException("Trying to set non-nullable field calorie to null.");
            } else {
                obj.setCalorie((int) json.getInt("calorie"));
            }
        }
        if (json.has("foodname")) {
            if (json.isNull("foodname")) {
                obj.setFoodname(null);
            } else {
                obj.setFoodname((String) json.getString("foodname"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static DataPackage createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        DataPackage obj = realm.createObject(DataPackage.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("sno")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field sno to null.");
                } else {
                    obj.setSno((int) reader.nextInt());
                }
            } else if (name.equals("date")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setDate(null);
                } else {
                    obj.setDate((String) reader.nextString());
                }
            } else if (name.equals("money")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field money to null.");
                } else {
                    obj.setMoney((int) reader.nextInt());
                }
            } else if (name.equals("calorie")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field calorie to null.");
                } else {
                    obj.setCalorie((int) reader.nextInt());
                }
            } else if (name.equals("foodname")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setFoodname(null);
                } else {
                    obj.setFoodname((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static DataPackage copyOrUpdate(Realm realm, DataPackage object, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        if (object.realm != null && object.realm.getPath().equals(realm.getPath())) {
            return object;
        }
        DataPackage realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(DataPackage.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = table.findFirstLong(pkColumnIndex, object.getSno());
            if (rowIndex != TableOrView.NO_MATCH) {
                realmObject = new DataPackageRealmProxy(realm.schema.getColumnInfo(DataPackage.class));
                realmObject.realm = realm;
                realmObject.row = table.getUncheckedRow(rowIndex);
                cache.put(object, (RealmObjectProxy) realmObject);
            } else {
                canUpdate = false;
            }
        }

        if (canUpdate) {
            return update(realm, realmObject, object, cache);
        } else {
            return copy(realm, object, update, cache);
        }
    }

    public static DataPackage copy(Realm realm, DataPackage newObject, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        DataPackage realmObject = realm.createObject(DataPackage.class, newObject.getSno());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        realmObject.setSno(newObject.getSno());
        realmObject.setDate(newObject.getDate());
        realmObject.setMoney(newObject.getMoney());
        realmObject.setCalorie(newObject.getCalorie());
        realmObject.setFoodname(newObject.getFoodname());
        return realmObject;
    }

    public static DataPackage createDetachedCopy(DataPackage realmObject, int currentDepth, int maxDepth, Map<RealmObject, CacheData<RealmObject>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<DataPackage> cachedObject = (CacheData) cache.get(realmObject);
        DataPackage standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return cachedObject.object;
            } else {
                standaloneObject = cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new DataPackage();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmObject>(currentDepth, standaloneObject));
        }
        standaloneObject.setSno(realmObject.getSno());
        standaloneObject.setDate(realmObject.getDate());
        standaloneObject.setMoney(realmObject.getMoney());
        standaloneObject.setCalorie(realmObject.getCalorie());
        standaloneObject.setFoodname(realmObject.getFoodname());
        return standaloneObject;
    }

    static DataPackage update(Realm realm, DataPackage realmObject, DataPackage newObject, Map<RealmObject, RealmObjectProxy> cache) {
        realmObject.setDate(newObject.getDate());
        realmObject.setMoney(newObject.getMoney());
        realmObject.setCalorie(newObject.getCalorie());
        realmObject.setFoodname(newObject.getFoodname());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!isValid()) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("DataPackage = [");
        stringBuilder.append("{sno:");
        stringBuilder.append(getSno());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{date:");
        stringBuilder.append(getDate() != null ? getDate() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{money:");
        stringBuilder.append(getMoney());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{calorie:");
        stringBuilder.append(getCalorie());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{foodname:");
        stringBuilder.append(getFoodname() != null ? getFoodname() : "null");
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        String realmName = realm.getPath();
        String tableName = row.getTable().getName();
        long rowIndex = row.getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataPackageRealmProxy aDataPackage = (DataPackageRealmProxy)o;

        String path = realm.getPath();
        String otherPath = aDataPackage.realm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = row.getTable().getName();
        String otherTableName = aDataPackage.row.getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (row.getIndex() != aDataPackage.row.getIndex()) return false;

        return true;
    }

}
