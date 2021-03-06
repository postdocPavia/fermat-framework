package unit.UnholdCryptoMoneyTransactionImpl;

import com.bitdubai.fermat_api.layer.all_definition.enums.CryptoCurrency;
import com.bitdubai.fermat_ccp_plugin.layer.crypto_transaction.unhold.developer.bitdubai.version_1.utils.UnHoldCryptoMoneyTransactionImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;

/**
 * Created by José Vilchez on 21/01/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class SetCurrencyTest {

    @Test
    public void setCurrency(){
        UnHoldCryptoMoneyTransactionImpl unHoldCryptoMoneyTransaction = mock(UnHoldCryptoMoneyTransactionImpl.class, Mockito.RETURNS_DEEP_STUBS);
        doCallRealMethod().when(unHoldCryptoMoneyTransaction).setCurrency(Mockito.any(CryptoCurrency.class));
    }

}
