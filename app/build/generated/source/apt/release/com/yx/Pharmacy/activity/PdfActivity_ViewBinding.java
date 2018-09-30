// Generated code from Butter Knife. Do not modify!
package com.yx.Pharmacy.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.barteksc.pdfviewer.PDFView;
import com.yx.Pharmacy.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PdfActivity_ViewBinding implements Unbinder {
  private PdfActivity target;

  @UiThread
  public PdfActivity_ViewBinding(PdfActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PdfActivity_ViewBinding(PdfActivity target, View source) {
    this.target = target;

    target.rlBack = Utils.findRequiredViewAsType(source, R.id.rl_back, "field 'rlBack'", RelativeLayout.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.pvPdf = Utils.findRequiredViewAsType(source, R.id.pv_pdf, "field 'pvPdf'", PDFView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PdfActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlBack = null;
    target.tvTitle = null;
    target.pvPdf = null;
  }
}
