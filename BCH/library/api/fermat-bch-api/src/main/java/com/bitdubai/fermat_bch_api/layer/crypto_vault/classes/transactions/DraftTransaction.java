package com.bitdubai.fermat_bch_api.layer.crypto_vault.classes.transactions;

import com.bitdubai.fermat_api.layer.all_definition.enums.BlockchainNetworkType;
import com.bitdubai.fermat_api.layer.all_definition.enums.CryptoCurrency;
import com.bitdubai.fermat_api.layer.all_definition.money.CryptoAddress;
import com.bitdubai.fermat_bch_api.layer.crypto_network.bitcoin.BitcoinNetworkSelector;

import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionInput;
import org.bitcoinj.core.TransactionOutput;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rodrigo on 2/10/16.
 */
public class DraftTransaction {
    private Transaction bitcoinTransaction;

    /**
     * constructor
     * @param bitcoinTransaction the initial bitcoin transaction with some inputs and outputs and unsigned.
     */
    public DraftTransaction(Transaction bitcoinTransaction) {
        this.bitcoinTransaction = bitcoinTransaction;
    }

    public static DraftTransaction deserialize(BlockchainNetworkType networkType, byte[] serializedTransaction) {
        return new DraftTransaction(new Transaction(BitcoinNetworkSelector.getNetworkParameter(networkType), serializedTransaction));
    }

    /**
     * Gets the funds used on this transaction by getting each individual input fund.
     * @return
     */
    public long getValue(){
        long totalValue = 0;
        for (TransactionInput transactionInput : bitcoinTransaction.getInputs()){
            totalValue = totalValue + transactionInput.getValue().getValue();
        }

        return totalValue;
    }


    /**
     * returns the funds that are distributed for each CryptoAddress specified in the transaction
     * @return
     */
    public Map<CryptoAddress, Long> getFundsDistribution(){
        Map<CryptoAddress, Long> fundsDistribution = new HashMap<>();

        for (TransactionOutput output : bitcoinTransaction.getOutputs()){
            if (output.getScriptPubKey().isSentToAddress()){
                CryptoAddress cryptoAddress = new CryptoAddress();
                cryptoAddress.setAddress(output.getScriptPubKey().getToAddress(bitcoinTransaction.getParams()).toString());
                cryptoAddress.setCryptoCurrency(CryptoCurrency.BITCOIN);

                long value = output.getValue().value;

                fundsDistribution.put(cryptoAddress, value);
            }
        }

        return fundsDistribution;
    }

    /**
     * getters
     */
    public String getTxHash() {
        return bitcoinTransaction.getHashAsString();
    }

    public BlockchainNetworkType getNetworkType() {
        return BitcoinNetworkSelector.getBlockchainNetworkType(bitcoinTransaction.getParams());
    }
    /**
     * Serializes the bitcoin transaction
     * @return
     */
    public byte[] serialize(){
        return bitcoinTransaction.bitcoinSerialize();
    }


}