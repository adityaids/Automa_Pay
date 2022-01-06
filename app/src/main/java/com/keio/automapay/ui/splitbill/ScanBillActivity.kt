package com.keio.automapay.ui.splitbill

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.keio.automapay.R
import com.keio.automapay.core.data.domain.model.SplitBillModel
import com.keio.automapay.databinding.ActivitySplitBillBinding
import com.keio.automapay.util.DummyData

class ScanBillActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplitBillBinding
    private lateinit var codeScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplitBillBinding.inflate(layoutInflater)
        setContentView(binding.root)

        codeScanner = CodeScanner(this, binding.scannerView)

        // Parameters (default values)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                validateResult(it.text)
                Toast.makeText(this, "Scan result: ${it.text}", Toast.LENGTH_LONG).show()
            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            runOnUiThread {
                Toast.makeText(this, "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG).show()
            }
        }

        binding.scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }
    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun toDetailBill(data: SplitBillModel){
        val intent = Intent(this@ScanBillActivity, DetailBillActivity::class.java).apply {
            putExtra(DetailBillActivity.RESULT_DATA, data)
        }
        startActivity(intent)
    }

    private fun validateResult(result: String){
        val resultData = DummyData.getDummySplitBills(result)
        if (resultData != null) {
            toDetailBill(resultData)
        } else {
            Toast.makeText(this, "Something wrong happen :(", Toast.LENGTH_LONG).show()
        }
    }
}