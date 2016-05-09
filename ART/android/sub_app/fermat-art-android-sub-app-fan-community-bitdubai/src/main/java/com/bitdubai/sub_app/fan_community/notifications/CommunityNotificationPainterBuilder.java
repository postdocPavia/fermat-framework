package com.bitdubai.sub_app.fan_community.notifications;

import com.bitdubai.fermat_android_api.engine.NotificationPainter;
import com.bitdubai.fermat_android_api.layer.definition.wallet.abstracts.AbstractFermatSession;
import com.bitdubai.fermat_api.layer.actor_connection.common.structure_abstract_classes.LinkedActorIdentity;
import com.bitdubai.fermat_api.layer.all_definition.exceptions.InvalidParameterException;
import com.bitdubai.fermat_art_api.layer.actor_connection.artist.enums.ArtistActorConnectionNotificationType;
import com.bitdubai.fermat_art_api.layer.actor_connection.fan.enums.FanActorConnectionNotificationType;
import com.bitdubai.fermat_art_api.layer.actor_connection.fan.utils.FanLinkedActorIdentity;
import com.bitdubai.fermat_art_api.layer.sub_app_module.community.artist.interfaces.ArtistCommunityInformation;
import com.bitdubai.fermat_art_api.layer.sub_app_module.community.artist.interfaces.ArtistCommunitySubAppModuleManager;
import com.bitdubai.fermat_art_api.layer.sub_app_module.community.fan.interfaces.FanCommunityInformation;
import com.bitdubai.fermat_art_api.layer.sub_app_module.community.fan.interfaces.FanCommunityModuleManager;
import com.bitdubai.fermat_art_api.layer.sub_app_module.community.fan.interfaces.LinkedFanIdentity;
import com.bitdubai.sub_app.fan_community.R;
import com.bitdubai.sub_app.fan_community.sessions.FanCommunitySubAppSession;

import java.util.List;

/**
 * Created by Manuel Perez (darkpriestrelative@gmail.com) on 05/04/16.
 */
public class CommunityNotificationPainterBuilder {
    public static NotificationPainter getNotification(
            final String code, AbstractFermatSession session) {
        NotificationPainter notification = null;
        int lastListIndex;
        if(session!=null){
            FanCommunitySubAppSession fanSubAppSession = (FanCommunitySubAppSession) session;
            FanCommunityModuleManager moduleManager = fanSubAppSession.getModuleManager();
            try{
                FanActorConnectionNotificationType notificationType = FanActorConnectionNotificationType.getByCode(code);
                if(moduleManager==null){
                    return getDefaultNotification(code);
                }
                switch (notificationType) {
                    case CONNECTION_REQUEST_RECEIVED:
                        List<LinkedFanIdentity> fanPendingList= moduleManager.
                                listFansPendingLocalAction(
                                        moduleManager.getSelectedActorIdentity(),
                                        20,
                                        0);
                        lastListIndex = fanPendingList.size()-1;
                        LinkedFanIdentity linkedFanIdentity = fanPendingList.get(
                                lastListIndex);
                        notification = new CommunityNotificationPainter(
                                "Artist Community",
                                "A Fermat Artist, "+linkedFanIdentity.getAlias()+", " +
                                        "wants to connect with you.",
                                "",
                                "",
                                R.drawable.afc_ic_nav_connections);
                        break;
                    case ACTOR_CONNECTED:
                        List<FanCommunityInformation> fanConnectedList= moduleManager.listAllConnectedFans(
                                moduleManager.getSelectedActorIdentity(),
                                20,
                                0);
                        lastListIndex = fanConnectedList.size()-1;
                        FanCommunityInformation fanConnectedInformation = fanConnectedList.get(
                                lastListIndex);
                        notification = new CommunityNotificationPainter(
                                "Artist Community",
                                "A Fermat Artist, "+fanConnectedInformation.getAlias()+", accepts" +
                                        " your connection request.",
                                "",
                                "",
                                R.drawable.afc_ic_nav_connections);
                        break;

                }
            } catch (Exception e) {
                //TODO: improve this catch
                e.printStackTrace();
            }
        }else{
            try {
                notification = getDefaultNotification(code);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return notification;
    }

    /**
     * This method returns a Fan community notification
     * @param code
     * @return
     * @throws InvalidParameterException
     */
    private static NotificationPainter getDefaultNotification(final String code)
            throws InvalidParameterException {
        FanActorConnectionNotificationType notificationType = FanActorConnectionNotificationType.getByCode(code);
        NotificationPainter notification = null;
        switch (notificationType) {
            case CONNECTION_REQUEST_RECEIVED:
                notification = new CommunityNotificationPainter(
                        "Fan Community",
                        "A Fermat Fan wants to connect with you.",
                        "",
                        "",
                        R.drawable.afc_ic_nav_connections);
                break;
            case ACTOR_CONNECTED:
                notification = new CommunityNotificationPainter(
                        "Artist Community",
                        "A Fermat Fan accept your connection request.",
                        "",
                        "",
                        R.drawable.afc_ic_nav_connections);
                break;
        }
        return notification;
    }
}
