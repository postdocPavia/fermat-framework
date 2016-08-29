package com.bitdubai.fermat_cht_plugin.layer.middleware.chat.developer.bitdubai.version_1.database;

import com.bitdubai.fermat_api.layer.osa_android.database_system.Database;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseDataType;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseFactory;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseTableFactory;
import com.bitdubai.fermat_api.layer.osa_android.database_system.PluginDatabaseSystem;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantCreateDatabaseException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantCreateTableException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.InvalidOwnerIdException;

import java.util.UUID;

/**
 * The Class  <code>com.bitdubai.fermat_cht_plugin.layer.middleware.chat.version_1.database.ChatMiddlewareDatabaseFactory</code>
 * is responsible for creating the tables in the database where it is to keep the information.
 * <p/>
 * <p/>
 * Created by Miguel Payarez - (miguel_payarez@hotmail.com) on 05/01/16.
 * Edited by Miguel Rincon on 19/04/2016
 *
 * @version 1.0
 * @since Java JDK 1.7
 */
public class ChatMiddlewareDatabaseFactory {

    /**
     * DealsWithPluginDatabaseSystem Interface member variables.
     */
    private PluginDatabaseSystem pluginDatabaseSystem;

    /**
     * Constructor with parameters to instantiate class
     * .
     *
     * @param pluginDatabaseSystem DealsWithPluginDatabaseSystem
     */
    public ChatMiddlewareDatabaseFactory(PluginDatabaseSystem pluginDatabaseSystem) {
        this.pluginDatabaseSystem = pluginDatabaseSystem;
    }

    /**
     * Create the database
     *
     * @param ownerId      the owner id
     * @param databaseName the database name
     * @return Database
     * @throws CantCreateDatabaseException
     */
    public Database createDatabase(UUID ownerId, String databaseName) throws CantCreateDatabaseException {
        Database database;
        /**
         * I will create the database where I am going to store the information of this wallet.
         */
        try {
            database = this.pluginDatabaseSystem.createDatabase(ownerId, databaseName);
        } catch (CantCreateDatabaseException cantCreateDatabaseException) {
            /**
             * I can not handle this situation.
             */
            throw new CantCreateDatabaseException(CantCreateDatabaseException.DEFAULT_MESSAGE, cantCreateDatabaseException, "", "Exception not handled by the plugin, There is a problem and i cannot create the database.");
        }
        /**
         * Next, I will add the needed tables.
         */
        try {
            DatabaseTableFactory table;
            DatabaseFactory databaseFactory = database.getDatabaseFactory();
            /**
             * Create Chats table.
             */
            table = databaseFactory.newTableFactory(ownerId, ChatMiddlewareDatabaseConstants.CHATS_TABLE_NAME);

            table.addColumn(ChatMiddlewareDatabaseConstants.CHATS_ID_CHAT_COLUMN_NAME, DatabaseDataType.STRING, 50, Boolean.TRUE);
            table.addColumn(ChatMiddlewareDatabaseConstants.CHATS_ID_OBJECT_COLUMN_NAME, DatabaseDataType.STRING, 50, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.CHATS_LOCAL_ACTOR_TYPE_COLUMN_NAME, DatabaseDataType.STRING, 100, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.CHATS_LOCAL_ACTOR_PUB_KEY_COLUMN_NAME, DatabaseDataType.STRING, 100, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.CHATS_REMOTE_ACTOR_TYPE_COLUMN_NAME, DatabaseDataType.STRING, 100, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.CHATS_REMOTE_ACTOR_PUB_KEY_COLUMN_NAME, DatabaseDataType.STRING, 100, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.CHATS_CHAT_NAME_COLUMN_NAME, DatabaseDataType.STRING, 100, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.CHATS_STATUS_COLUMN_NAME, DatabaseDataType.STRING, 10, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.CHATS_CREATION_DATE_COLUMN_NAME, DatabaseDataType.STRING, 50, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.CHATS_LAST_MESSAGE_DATE_COLUMN_NAME, DatabaseDataType.STRING, 50, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.CHATS_CONTACT_ASSOCIATED_LIST, DatabaseDataType.STRING, 256, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.CHATS_TYPE_CHAT, DatabaseDataType.STRING, 10, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.CHATS_SCHEDULED_DELIVERY, DatabaseDataType.STRING, 10, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.CHATS_IS_WRITING, DatabaseDataType.STRING, 10, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.CHATS_IS_ONLINE, DatabaseDataType.STRING, 10, Boolean.FALSE);

            table.addIndex(ChatMiddlewareDatabaseConstants.CHATS_FIRST_KEY_COLUMN);

            try {
                //Create the table
                databaseFactory.createTable(ownerId, table);
            } catch (CantCreateTableException cantCreateTableException) {
                throw new CantCreateDatabaseException(CantCreateDatabaseException.DEFAULT_MESSAGE, cantCreateTableException, "", "Exception not handled by the plugin, There is a problem and i cannot create the table.");
            }
            /**
             * Create Message table.
             */
            table = databaseFactory.newTableFactory(ownerId, ChatMiddlewareDatabaseConstants.MESSAGE_TABLE_NAME);

            table.addColumn(ChatMiddlewareDatabaseConstants.MESSAGE_ID_MESSAGE_COLUMN_NAME, DatabaseDataType.STRING, 50, Boolean.TRUE);
            table.addColumn(ChatMiddlewareDatabaseConstants.MESSAGE_ID_CHAT_COLUMN_NAME, DatabaseDataType.STRING, 50, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.MESSAGE_TEXT_MESSAGE_COLUMN_NAME, DatabaseDataType.STRING, 100, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.MESSAGE_STATUS_COLUMN_NAME, DatabaseDataType.STRING, 10, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.MESSAGE_TYPE_COLUMN_NAME, DatabaseDataType.STRING, 10, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.MESSAGE_MESSAGE_DATE_COLUMN_NAME, DatabaseDataType.STRING, 50, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.MESSAGE_CONTACT_ID, DatabaseDataType.STRING, 36, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.MESSAGE_COUNT, DatabaseDataType.STRING, 36, Boolean.FALSE);

            table.addIndex(ChatMiddlewareDatabaseConstants.MESSAGE_FIRST_KEY_COLUMN);

            try {
                //Create the table
                databaseFactory.createTable(ownerId, table);
            } catch (CantCreateTableException cantCreateTableException) {
                throw new CantCreateDatabaseException(CantCreateDatabaseException.DEFAULT_MESSAGE, cantCreateTableException, "", "Exception not handled by the plugin, There is a problem and i cannot create the table.");
            }

            /**
             * Create actions writing table.
             */
            table = databaseFactory.newTableFactory(ownerId, ChatMiddlewareDatabaseConstants.ACTIONS_WRITING_TABLE_NAME);

            table.addColumn(ChatMiddlewareDatabaseConstants.ACTIONS_WRITING_ID_CHAT_COLUMN_NAME, DatabaseDataType.STRING, 50, Boolean.TRUE);
            table.addColumn(ChatMiddlewareDatabaseConstants.ACTIONS_WRITING_STATE, DatabaseDataType.STRING, 10, Boolean.FALSE);

            table.addIndex(ChatMiddlewareDatabaseConstants.ACTIONS_WRITING_FIRST_KEY_COLUMN);

            try {
                //Create the table
                databaseFactory.createTable(ownerId, table);
            } catch (CantCreateTableException cantCreateTableException) {
                throw new CantCreateDatabaseException(CantCreateDatabaseException.DEFAULT_MESSAGE, cantCreateTableException, "", "Exception not handled by the plugin, There is a problem and i cannot create the table.");
            }

            /**
             * Create actions online table.
             */
            table = databaseFactory.newTableFactory(ownerId, ChatMiddlewareDatabaseConstants.ACTIONS_ONLINE_TABLE_NAME);

            table.addColumn(ChatMiddlewareDatabaseConstants.ACTIONS_ONLINE_ID_COLUMN_NAME, DatabaseDataType.STRING, 50, Boolean.TRUE);
            table.addColumn(ChatMiddlewareDatabaseConstants.ACTIONS_ONLINE_PUBLIC_KEY_COLUMN_NAME, DatabaseDataType.STRING, 50, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.ACTIONS_ONLINE_STATE, DatabaseDataType.STRING, 10, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.ACTIONS_ONLINE_VALUE, DatabaseDataType.STRING, 10, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.ACTIONS_ONLINE_LAST_ON, DatabaseDataType.STRING, 10, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.ACTIONS_LAST_CONNECTION, DatabaseDataType.STRING, 20, Boolean.FALSE);

            table.addIndex(ChatMiddlewareDatabaseConstants.ACTIONS_ONLINE_FIRST_KEY_COLUMN);

            try {
                //Create the table
                databaseFactory.createTable(ownerId, table);
            } catch (CantCreateTableException cantCreateTableException) {
                throw new CantCreateDatabaseException(CantCreateDatabaseException.DEFAULT_MESSAGE, cantCreateTableException, "", "Exception not handled by the plugin, There is a problem and i cannot create the table.");
            }

            /**
             * Create Events table.
             */
            table = databaseFactory.newTableFactory(ownerId, ChatMiddlewareDatabaseConstants.EVENTS_RECORDED_TABLE_NAME);

            table.addColumn(ChatMiddlewareDatabaseConstants.EVENTS_RECORDED_ID_COLUMN_NAME, DatabaseDataType.STRING, 36, Boolean.TRUE);
            table.addColumn(ChatMiddlewareDatabaseConstants.EVENTS_RECORDED_EVENT_COLUMN_NAME, DatabaseDataType.STRING, 10, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.EVENTS_RECORDED_SOURCE_COLUMN_NAME, DatabaseDataType.STRING, 10, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.EVENTS_RECORDED_STATUS_COLUMN_NAME, DatabaseDataType.STRING, 10, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.EVENTS_RECORDED_TIMESTAMP_COLUMN_NAME, DatabaseDataType.LONG_INTEGER, 100, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.EVENTS_RECORDED_CHAT_ID_COLUMN_NAME, DatabaseDataType.STRING, 36, Boolean.FALSE);

            table.addIndex(ChatMiddlewareDatabaseConstants.EVENTS_RECORDED_TABLE_FIRST_KEY_COLUMN);

            try {
                //Create the table
                databaseFactory.createTable(ownerId, table);
            } catch (CantCreateTableException cantCreateTableException) {
                throw new CantCreateDatabaseException(CantCreateDatabaseException.DEFAULT_MESSAGE, cantCreateTableException, "", "Exception not handled by the plugin, There is a problem and i cannot create the table.");
            }

            /**
             * Create Group Member table.
             */
            table = databaseFactory.newTableFactory(ownerId, ChatMiddlewareDatabaseConstants.GROUP_MEMBER_TABLE_NAME);

            table.addColumn(ChatMiddlewareDatabaseConstants.GROUP_MEMBER_ID_COLUMN_NAME, DatabaseDataType.STRING, 255, Boolean.TRUE);
            table.addColumn(ChatMiddlewareDatabaseConstants.GROUP_MEMBER_GROUP_ID_COLUMN_NAME, DatabaseDataType.STRING, 255, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.GROUP_MEMBER_USER_REGISTERED_PUBLIC_KEY_COLUMN_NAME, DatabaseDataType.STRING, 255, Boolean.FALSE);
            table.addColumn(ChatMiddlewareDatabaseConstants.GROUP_MEMBER_ALIAS_COLUMN_NAME, DatabaseDataType.STRING, 100, Boolean.FALSE);

            table.addIndex(ChatMiddlewareDatabaseConstants.GROUP_MEMBER_FIRST_KEY_COLUMN);

            try {
                //Create the table
                databaseFactory.createTable(ownerId, table);
            } catch (CantCreateTableException cantCreateTableException) {
                throw new CantCreateDatabaseException(CantCreateDatabaseException.DEFAULT_MESSAGE, cantCreateTableException, "", "Exception not handled by the plugin, There is a problem and i cannot create the table.");
            }

        } catch (InvalidOwnerIdException invalidOwnerId) {
            /**
             * This shouldn't happen here because I was the one who gave the owner id to the database file system,
             * but anyway, if this happens, I can not continue.
             */
            throw new CantCreateDatabaseException(CantCreateDatabaseException.DEFAULT_MESSAGE, invalidOwnerId, "", "There is a problem with the ownerId of the database.");
        }
        return database;
    }
}
