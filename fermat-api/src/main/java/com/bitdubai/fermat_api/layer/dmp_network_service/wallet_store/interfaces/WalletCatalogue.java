package com.bitdubai.fermat_api.layer.dmp_network_service.wallet_store.interfaces;

import com.bitdubai.fermat_api.layer.dmp_network_service.wallet_store.exceptions.CantGetWalletsCatalogueException;

import java.util.List;

/**
 * This interface represents the wallets catalogue
 *
 * @author Ezequiel Postan
 */
public interface WalletCatalogue {

    /**
     * This method give us a list of wallets in the catalogue
     *
     * @param offset the offset (position) in the catalogue where we stand
     * @param top the maximum number of wallets to get as a result
     * @return A list of at most "top" catalogue items (wallets)
     * @throws CantGetWalletsCatalogueException
     */
    public List<NewCatalogueItem> getWalletCatalogue(int offset, int top) throws CantGetWalletsCatalogueException;
}