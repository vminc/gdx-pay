package com.badlogic.gdx.pay.android.googleplay.billing.converter;

import com.badlogic.gdx.pay.PurchaseManagerConfig;
import com.badlogic.gdx.pay.Transaction;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import static com.badlogic.gdx.pay.android.googleplay.GoogleBillingConstants.ORDER_ID;
import static com.badlogic.gdx.pay.android.googleplay.GoogleBillingConstants.PRODUCT_ID;
import static com.badlogic.gdx.pay.android.googleplay.GoogleBillingConstants.PURCHASE_TIME;

public class InAppPurchaseDataToTransactionConverter {

    // See http://developer.android.com/google/play/billing/billing_reference.html#purchase-data-table
    public static Transaction convertJSONPurchaseToTransaction(String jsonPurchase) throws JSONException {
        JSONObject object = new JSONObject(jsonPurchase);

        Transaction transaction = new Transaction();
        transaction.setStoreName(PurchaseManagerConfig.STORE_NAME_ANDROID_GOOGLE);

        if (object.has(ORDER_ID)) {
            transaction.setOrderId(object.getString(ORDER_ID));
        }

        transaction.setIdentifier(object.getString(PRODUCT_ID));
        transaction.setPurchaseTime(new Date(object.getLong(PURCHASE_TIME)));

        return transaction;

    }
}
