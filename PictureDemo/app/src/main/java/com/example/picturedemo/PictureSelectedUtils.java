package com.example.picturedemo;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.style.PictureCropParameterStyle;
import com.luck.picture.lib.style.PictureParameterStyle;
import com.luck.picture.lib.style.PictureWindowAnimationStyle;
import java.util.List;

/**
 * TODO
 * shige chen on 2020/2/27
 * shigechen@globalsources.com
 */
public class PictureSelectedUtils {
    private PictureParameterStyle mPictureParameterStyle;
    private PictureCropParameterStyle mCropParameterStyle;
    private PictureWindowAnimationStyle mWindowAnimationStyle;

    private static PictureSelectedUtils mPictureSelectedUtils;
    private String TAG = "Picture";

    private PictureSelectedUtils() {
    }

    public static PictureSelectedUtils newInstance() {
        if (null == mPictureSelectedUtils) {
            synchronized (PictureSelectedUtils.class) {
                if (null == mPictureSelectedUtils) {
                    mPictureSelectedUtils = new PictureSelectedUtils();
                }
            }
        }
        return mPictureSelectedUtils;
    }

    /***
     *PictureMimeType.ofAll()
     * @param activity
     * @param chooseMode
     */
    /**
     * 单选
     */
    public void onShowSingleSelect(Activity activity, OnResultCallbackListener listener) {
        onShowSelectPicture(activity, PictureMimeType.ofImage(), 1, true, true, true, true, false, null, listener);
    }

    public void onShowSingleSelect(Activity activity, boolean isPreviewImage, boolean isCrop,
            OnResultCallbackListener listener) {
        onShowSelectPicture(activity, PictureMimeType.ofImage(), 1, true, false, isPreviewImage, true, isCrop, null,
                listener);
    }

    public void onShowMoreSelect(Activity activity, int maxSelectNum, boolean isPreviewImage,
            List<LocalMedia> selectionMedia, OnResultCallbackListener listener) {
        onShowSelectPicture(activity, PictureMimeType.ofImage(), maxSelectNum, false, false, isPreviewImage, true,
                false, selectionMedia, listener);
    }

    private void onShowSelectPicture(Activity activity, int chooseMode, int maxSelectNum, boolean isSingleSelect,
            boolean isSingleDirectReturn, boolean isPreviewImage, boolean isCamera, boolean isCrop,
            List<LocalMedia> selectionMedia, OnResultCallbackListener listener) {
        //设置样式
        getSinaStyle(activity);
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(activity)
                .openGallery(chooseMode)// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .loadImageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                //.theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style v2.3.3后 建议使用setPictureStyle()动态方式
                .isWeChatStyle(false)// 是否开启微信图片选择风格
                .isUseCustomCamera(false)// 是否使用自定义相机
                //.setLanguage(language)// 设置语言，默认中文
                .setPictureStyle(mPictureParameterStyle)// 动态自定义相册主题
                .setPictureCropStyle(mCropParameterStyle)// 动态自定义裁剪主题
                .setPictureWindowAnimationStyle(mWindowAnimationStyle)// 自定义相册启动退出动画
                //.isWithVideoImage(true)// 图片和视频是否可以同选,只在ofAll模式下有效
                .maxSelectNum(maxSelectNum)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .maxVideoSelectNum(1) // 视频最大选择数量，如果没有单独设置的需求则可以不设置，同用maxSelectNum字段
                //.minVideoSelectNum(1)// 视频最小选择数量，如果没有单独设置的需求则可以不设置，同用minSelectNum字段
                .imageSpanCount(4)// 每行显示个数
                .isReturnEmpty(false)// 未选择数据时点击按钮是否可以返回
                //.isAndroidQTransform(false)// 是否需要处理Android Q 拷贝至应用沙盒的操作，只针对compress(false); && enableCrop(false);有效,默认处理
                .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)// 设置相册Activity方向，不设置默认使用系统
                .isOriginalImageControl(false)// 是否显示原图控制按钮，如果设置为true则用户可以自由选择是否使用原图，压缩、裁剪功能将会失效
                //.bindCustomPlayVideoCallback(callback)// 自定义视频播放回调控制，用户可以使用自己的视频播放界面
                //.cameraFileName(System.currentTimeMillis() +".jpg")    // 重命名拍照文件名、如果是相册拍照则内部会自动拼上当前时间戳防止重复，注意这个只在使用相机时可以使用，如果使用相机又开启了压缩或裁剪 需要配合压缩和裁剪文件名api
                //.renameCompressFile(System.currentTimeMillis() +".jpg")// 重命名压缩文件名、 注意这个不要重复，只适用于单张图压缩使用
                //.renameCropFileName(System.currentTimeMillis() + ".jpg")// 重命名裁剪文件名、 注意这个不要重复，只适用于单张图裁剪使用
                //.selectionMode(cb_choose_mode.isChecked() ? PictureConfig.MULTIPLE : PictureConfig.SINGLE)// 多选 or 单选
                .selectionMode(isSingleSelect ? PictureConfig.SINGLE : PictureConfig.MULTIPLE)// 多选 or 单选
                .isSingleDirectReturn(isSingleDirectReturn)// 单选模式下是否直接返回，PictureConfig.SINGLE模式下有效
                .previewImage(isPreviewImage)// 是否可预览图片
                //.previewVideo(cb_preview_video.isChecked())// 是否可预览视频
                //.querySpecifiedFormatSuffix(PictureMimeType.ofJPEG())// 查询指定后缀格式资源
                //.enablePreviewAudio(cb_preview_audio.isChecked()) // 是否可播放音频
                .isCamera(isCamera)// 是否显示拍照按钮
                //.isMultipleSkipCrop(false)// 多图裁剪时是否支持跳过，默认支持
                //.isMultipleRecyclerAnimation(false)// 多图裁剪底部列表显示动画效果
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .enableCrop(isCrop)// 是否裁剪
                //.basicUCropConfig()//对外提供所有UCropOptions参数配制，但如果PictureSelector原本支持设置的还是会使用原有的设置
                .compress(true)// 是否压缩
                .compressQuality(80)// 图片压缩后输出质量 0~ 100
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                //.queryMaxFileSize(10)// 只查多少M以内的图片、视频、音频  单位M
                //.compressSavePath(getPath())//压缩图片保存地址
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效 注：已废弃
                //.glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度 注：已废弃
                .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                //.hideBottomControls(cb_hide.isChecked() ? false : true)// 是否显示uCrop工具栏，默认不显示
                //.isGif(cb_isGif.isChecked())// 是否显示gif图片
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                //.circleDimmedLayer(cb_crop_circular.isChecked())// 是否圆形裁剪
                //.setCircleDimmedColor(ContextCompat.getColor(getContext(), R.color.app_color_white))// 设置圆形裁剪背景色值
                //.setCircleDimmedBorderColor(ContextCompat.getColor(getApplicationContext(), R.color.app_color_white))// 设置圆形裁剪边框色值
                //.setCircleStrokeWidth(3)// 设置圆形裁剪边框粗细
                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .openClickSound(false)// 是否开启点击声音
                .selectionMedia(selectionMedia)// 是否传入已选图片
                .isDragFrame(true)// 是否可拖动裁剪框(固定)
                //.videoMinSecond(10)
                //.videoMaxSecond(15)
                //.recordVideoSecond(10)//录制视频秒数 默认60s
                //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                //.cropCompressQuality(90)// 注：已废弃 改用cutOutQuality()
                .cutOutQuality(90)// 裁剪输出质量 默认100
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                //.cropImageWideHigh()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                //.rotateEnabled(false) // 裁剪是否可旋转图片
                //.scaleEnabled(false)// 裁剪是否可放大缩小图片
                //.videoQuality()// 视频录制质量 0 or 1
                //.videoSecond()//显示多少秒以内的视频or音频也可适用
                //.forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                //.forResult(new OnResultCallbackListener() {
                //    @Override
                //    public void onResult(List<LocalMedia> result) {
                //        for (LocalMedia media : result) {
                //            Log.i(TAG, "是否压缩:" + media.isCompressed());
                //            Log.i(TAG, "压缩:" + media.getCompressPath());
                //            Log.i(TAG, "原图:" + media.getPath());
                //            Log.i(TAG, "是否裁剪:" + media.isCut());
                //            Log.i(TAG, "裁剪:" + media.getCutPath());
                //            Log.i(TAG, "是否开启原图:" + media.isOriginal());
                //            Log.i(TAG, "原图路径:" + media.getOriginalPath());
                //            Log.i(TAG, "Android Q 特有Path:" + media.getAndroidQToPath());
                //        }
                //        //mAdapter.setList(result);
                //        //mAdapter.notifyDataSetChanged();
                //    }
                //
                //    @Override
                //    public void onCancel() {
                //        Log.i(TAG, "PictureSelector Cancel");
                //    }
                //});
                .forResult(listener);
    }

    private void getSinaStyle(Context context) {
        //动画
        mWindowAnimationStyle = new PictureWindowAnimationStyle();
        // 相册主题
        mPictureParameterStyle = new PictureParameterStyle();
        mPictureParameterStyle.isHintFolderMenu = true;
        mPictureParameterStyle.isShowTvCancelBack = true;
        mPictureParameterStyle.pictureTitleText = "Photo";
        // 是否改变状态栏字体颜色(黑白切换)
        mPictureParameterStyle.isChangeStatusBarFontColor = true;
        // 是否开启右下角已完成(0/9)风格
        mPictureParameterStyle.isOpenCompletedNumStyle = true;
        // 是否开启类似QQ相册带数字选择风格
        mPictureParameterStyle.isOpenCheckNumStyle = false;
        // 相册状态栏背景色
        mPictureParameterStyle.pictureStatusBarColor = Color.parseColor("#FFFFFF");
        // 相册列表标题栏背景色
        mPictureParameterStyle.pictureTitleBarBackgroundColor = Color.parseColor("#FFFFFF");
        // 相册列表标题栏右侧上拉箭头
        //mPictureParameterStyle.pictureTitleUpResId = R.drawable.ic_orange_arrow_up;
        // 相册列表标题栏右侧下拉箭头
        //mPictureParameterStyle.pictureTitleDownResId = R.drawable.ic_orange_arrow_down;
        // 相册文件夹列表选中圆点
        mPictureParameterStyle.pictureFolderCheckedDotStyle = R.drawable.picture_orange_oval;
        // 相册返回箭头
        //mPictureParameterStyle.pictureLeftBackIcon = R.drawable.ic_back_arrow;
        // 标题栏字体颜色
        mPictureParameterStyle.pictureTitleTextColor = ContextCompat.getColor(context, R.color.app_color_black2);
        //标题字体大小
        mPictureParameterStyle.pictureTitleTextSize = 18;
        // 相册右侧取消按钮字体颜色  废弃 改用.pictureRightDefaultTextColor和.pictureRightDefaultTextColor
        //mPictureParameterStyle.pictureCancelTextColor = ContextCompat.getColor(getContext(), R.color.app_color_black);
        // 相册列表勾选图片样式
        mPictureParameterStyle.pictureCheckedStyle = R.drawable.picture_checkbox_selector;
        // 相册列表底部背景色
        mPictureParameterStyle.pictureBottomBgColor = ContextCompat.getColor(context, R.color.picture_color_white);
        // 已选数量圆点背景样式
        mPictureParameterStyle.pictureCheckNumBgStyle = R.drawable.picture_num_oval;
        // 相册列表底下预览文字色值(预览按钮可点击时的色值)
        //mPictureParameterStyle.picturePreviewTextColor = ContextCompat.getColor(getContext(), R.color.picture_color_fa632d);
        // 相册列表底下不可预览文字色值(预览按钮不可点击时的色值)
        //mPictureParameterStyle.pictureUnPreviewTextColor = ContextCompat.getColor(getContext(), R.color.picture_color_9b);
        // 相册列表已完成色值(已完成 可点击色值)
        //mPictureParameterStyle.pictureCompleteTextColor = ContextCompat.getColor(getContext(), R.color.picture_color_fa632d);
        // 相册列表未完成色值(请选择 不可点击色值)
        //mPictureParameterStyle.pictureUnCompleteTextColor = ContextCompat.getColor(getContext(), R.color.picture_color_9b);
        // 预览界面底部背景色
        //mPictureParameterStyle.picturePreviewBottomBgColor = ContextCompat.getColor(getContext(), R.color.picture_color_fa);
        // 原图按钮勾选样式  需设置.isOriginalImageControl(true); 才有效
        mPictureParameterStyle.pictureOriginalControlStyle = R.drawable.picture_original_checkbox;
        // 原图文字颜色 需设置.isOriginalImageControl(true); 才有效
        //mPictureParameterStyle.pictureOriginalFontColor = ContextCompat.getColor(getContext(), R.color.app_color_53575e);
        // 外部预览界面删除按钮样式
        mPictureParameterStyle.pictureExternalPreviewDeleteStyle = R.drawable.picture_icon_black_delete;
        // 外部预览界面是否显示删除按钮
        mPictureParameterStyle.pictureExternalPreviewGonePreviewDelete = true;
        //        // 自定义相册右侧文本内容设置
        //        mPictureParameterStyle.pictureRightDefaultText = "";
        // 完成文案是否采用(%1$d/%2$d)的字符串，只允许俩个占位符哟
        mPictureParameterStyle.isCompleteReplaceNum = false;
        mPictureParameterStyle.isCompletePreview = false;
        mPictureParameterStyle.isPictureBottomConfirm = true;
        //        // 自定义相册未完成文本内容
        //        mPictureParameterStyle.pictureUnCompleteText = "请选择";
        // 自定义相册完成文本内容，已经支持两个占位符String 但isCompleteReplaceNum必须为true
        //        mPictureParameterStyle.pictureCompleteText = getString(R.string.app_wechat_send_num);
        //        // 自定义相册列表不可预览文字
        //        mPictureParameterStyle.pictureUnPreviewText = "";
        //        // 自定义相册列表预览文字
        //        mPictureParameterStyle.picturePreviewText = "";

        //        // 自定义相册标题字体大小
        //        mPictureParameterStyle.pictureTitleTextSize = 18;
        //        // 自定义相册右侧文字大小
        //        mPictureParameterStyle.pictureRightTextSize = 14;
        //        // 自定义相册预览文字大小
        //        mPictureParameterStyle.picturePreviewTextSize = 14;
        //        // 自定义相册完成文字大小
        //        mPictureParameterStyle.pictureCompleteTextSize = 14;
        //        // 自定义原图文字大小
        //        mPictureParameterStyle.pictureOriginalTextSize = 14;
        // 裁剪主题
        mCropParameterStyle = new PictureCropParameterStyle(ContextCompat.getColor(context, R.color.app_color_white),
                ContextCompat.getColor(context, R.color.app_color_white),
                ContextCompat.getColor(context, R.color.app_color_black),
                mPictureParameterStyle.isChangeStatusBarFontColor);
    }
}
