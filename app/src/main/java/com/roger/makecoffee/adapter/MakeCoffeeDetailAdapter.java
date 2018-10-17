package com.roger.makecoffee.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.roger.makecoffee.R;
import com.roger.makecoffee.makecoffeedetail.MakeCoffeeDetailFragment;
import com.roger.makecoffee.objects.define.MakeCoffeeTeaching;
import com.roger.makecoffee.utils.Constants;
import com.sdsmdg.harjot.crollerTest.Croller;
import com.sdsmdg.harjot.crollerTest.OnCrollerChangeListener;

import java.util.Timer;
import java.util.TimerTask;

public class MakeCoffeeDetailAdapter extends RecyclerView.Adapter {
    public static Timer coffeeDetailTimer;
    private static final int PREPARE = 0;
    private static final int WATER_TEMP = 1;
    private static final int TIMING = 2;
    private static final int NORMAL = 3;
    private static final int COMPLETED = 4;

    private MakeCoffeeDetailFragment mFragment;
    private MakeCoffeeTeaching mTeaching;

    public MakeCoffeeDetailAdapter(MakeCoffeeDetailFragment fragment, MakeCoffeeTeaching teaching) {
        mFragment = fragment;
        mTeaching = teaching;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == PREPARE) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_make_coffee_detail_prepare, parent, false);
            return new PrepareViewHolder(view);
        } else if (viewType == WATER_TEMP) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_make_coffee_detail_water_temp, parent, false);
            return new WaterTempViewHolder(view);
        } else if (viewType == TIMING) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_make_coffee_detail_timing, parent, false);
            return new TimingViewHolder(view);
        } else if (viewType == NORMAL) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_make_coffee_detail_normal, parent, false);
            return new NormalViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_make_coffee_detail_completed, parent, false);
            return new CompletedViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PrepareViewHolder) {
            bindPrepareViewHolder((PrepareViewHolder) holder, position);
        } else if (holder instanceof WaterTempViewHolder) {
            bindWaterTempViewHolder((WaterTempViewHolder) holder, position);
        } else if (holder instanceof TimingViewHolder) {
            bindTimingViewHolder((TimingViewHolder) holder, position);
        } else if (holder instanceof NormalViewHolder) {
            bindNormalViewHolder((NormalViewHolder) holder, position);
        } else if (holder instanceof CompletedViewHolder) {
            bindCompletedViewHolder((CompletedViewHolder) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return mTeaching.getMakeCoffeeStepsArrayList().size();
    }

    @Override
    public int getItemViewType(int position) {
        String stepType = mTeaching.getMakeCoffeeStepsArrayList().get(position).getStepType();
        switch (stepType) {
            case Constants.PREPARE:
                return PREPARE;

            case Constants.WATER_TEMP:
                return WATER_TEMP;

            case Constants.TIMING:
                return TIMING;

            case Constants.NORMAL:
                return NORMAL;

            case Constants.COMPLETED:
                return COMPLETED;

            default:
                return COMPLETED;
        }
    }

    private void bindPrepareViewHolder(PrepareViewHolder holder, int position) {
        String currentStep = "(" + (position + 1) + "/" + mTeaching.getMakeCoffeeStepsArrayList().size() + ")";
        holder.currentStepTextView.setText(currentStep);
        holder.contentTextView.setText(mTeaching.getMakeCoffeeStepsArrayList().get(position).getContent());
    }

    private void bindWaterTempViewHolder(WaterTempViewHolder holder, int position) {
        String currentStep = "(" + (position + 1) + "/" + mTeaching.getMakeCoffeeStepsArrayList().size() + ")";
        holder.currentStepTextView.setText(currentStep);
        holder.contentTextView.setText(mTeaching.getMakeCoffeeStepsArrayList().get(position).getContent());
    }

    private void bindTimingViewHolder(TimingViewHolder holder, int position) {
        String currentStep = "(" + (position + 1) + "/" + mTeaching.getMakeCoffeeStepsArrayList().size() + ")";
        holder.currentStepTextView.setText(currentStep);
        holder.setCrollerMaxTime(mTeaching.getMakeCoffeeStepsArrayList().get(position).getOtherMessage());
    }

    private void bindNormalViewHolder(NormalViewHolder holder, int position) {
        String currentStep = "(" + (position + 1) + "/" + mTeaching.getMakeCoffeeStepsArrayList().size() + ")";
        holder.currentStepTextView.setText(currentStep);
        holder.contentTextView.setText(mTeaching.getMakeCoffeeStepsArrayList().get(position).getContent());
    }

    private void bindCompletedViewHolder(CompletedViewHolder holder, int position) {
        String currentStep = "(" + (position + 1) + "/" + mTeaching.getMakeCoffeeStepsArrayList().size() + ")";
        holder.currentStepTextView.setText(currentStep);
        holder.contentTextView.setText(mTeaching.getMakeCoffeeStepsArrayList().get(position).getContent());
    }

    public void destroyTimer() {
        if (coffeeDetailTimer != null) {
            coffeeDetailTimer.cancel();
            coffeeDetailTimer = null;
        }
    }

    private class PrepareViewHolder extends RecyclerView.ViewHolder {
        TextView currentStepTextView;
        TextView contentTextView;

        PrepareViewHolder(View itemView) {
            super(itemView);
            currentStepTextView = itemView.findViewById(R.id.textView_coffee_detail_prepare_current_step);
            contentTextView = itemView.findViewById(R.id.textView_coffee_detail_prepare);
        }
    }

    public class WaterTempViewHolder extends RecyclerView.ViewHolder {
        TextView currentStepTextView;
        TextView contentTextView;

        WaterTempViewHolder(View itemView) {
            super(itemView);
            currentStepTextView = itemView.findViewById(R.id.textView_coffee_detail_water_temp_current_step);
            contentTextView = itemView.findViewById(R.id.textView_coffee_detail_water_temp);
        }
    }

    public class TimingViewHolder extends RecyclerView.ViewHolder {
        TextView currentStepTextView;
        Croller croller;
        TextView countTextView;
        Button startButton;
        Button pauseAndContinueButton;
        Button restartButton;
        Button singleRestartButton;
        boolean isCounting = false;
        int maxTime;
        int currentTime;

        TimingViewHolder(View itemView) {
            super(itemView);
            currentStepTextView = itemView.findViewById(R.id.textView_coffee_detail_timing_current_step);
            croller = itemView.findViewById(R.id.croller_coffee_detail_timing);
            countTextView = itemView.findViewById(R.id.textView_coffee_detail_timing_count);
            startButton = itemView.findViewById(R.id.button_coffee_detail_timing_start);
            pauseAndContinueButton = itemView.findViewById(R.id.button_coffee_detail_timing_pause);
            restartButton = itemView.findViewById(R.id.button_coffee_detail_timing_restart);
            singleRestartButton = itemView.findViewById(R.id.button_coffee_detail_timing_single_restart);

            startButton.setOnClickListener(clickListener);
            pauseAndContinueButton.setOnClickListener(clickListener);
            restartButton.setOnClickListener(clickListener);
            singleRestartButton.setOnClickListener(clickListener);

            croller.setOnCrollerChangeListener(new OnCrollerChangeListener() {
                @Override
                public void onProgressChanged(Croller croller, int progress) {
                    currentTime = progress;
                    setCountTime(currentTime);
                    if (progress <= 0) {
                        onCrollerStartTrackingTouch();
                    }
                }

                @Override
                public void onStartTrackingTouch(Croller croller) {

                }

                @Override
                public void onStopTrackingTouch(Croller croller) {

                }
            });

        }

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_coffee_detail_timing_start:
                        startCroller();
                        break;

                    case R.id.button_coffee_detail_timing_pause:
                        pauseOrContinueCroller();
                        break;

                    case R.id.button_coffee_detail_timing_restart:
                        restartCroller();
                        break;

                    case R.id.button_coffee_detail_timing_single_restart:
                        restartCroller();
                        break;

                    default:
                }
            }
        };

        void setCrollerMaxTime(String timeInSec) {
            int time = Integer.parseInt(timeInSec);
            croller.setMax(time);
            croller.setProgress(time);
            setCountTime(time);
            maxTime = time;
            currentTime = time;
        }

        void setCountTime(int timeInSec) {
            String count = timeInSec + "s";
            countTextView.setText(count);
        }

        void startCroller() {
            isCounting = true;
            startTimer();
            showPauseAndRestartButton();

        }

        void pauseOrContinueCroller() {
            if (isCounting) {
                isCounting = false;
                showContinueAndRestartButton();
                destroyTimer();
            } else {
                isCounting = true;
                showPauseAndRestartButton();
                startTimer();
            }
        }

        void onCrollerStartTrackingTouch() {
            isCounting = false;
            destroyTimer();
            showSingleRestartButton();
        }

        void restartCroller() {
            destroyTimer();
            showStartButton();
            setCrollerMaxTime(String.valueOf(maxTime));
        }

        void startTimer() {
            coffeeDetailTimer = new Timer();
            coffeeDetailTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Log.d("counter", "time: " + currentTime);
                    currentTime --;
                    mFragment.getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    setCountTime(currentTime);
                                    croller.setProgress(currentTime);
                                }
                            });
                }
            }, 0, 1000);
        }

        void showStartButton() {
            startButton.setVisibility(View.VISIBLE);
            pauseAndContinueButton.setVisibility(View.GONE);
            restartButton.setVisibility(View.GONE);
            singleRestartButton.setVisibility(View.GONE);
        }

        void showPauseAndRestartButton() {
            pauseAndContinueButton.setText(R.string.pause);
            startButton.setVisibility(View.GONE);
            pauseAndContinueButton.setVisibility(View.VISIBLE);
            restartButton.setVisibility(View.VISIBLE);
            singleRestartButton.setVisibility(View.GONE);
        }

        void showContinueAndRestartButton() {
            pauseAndContinueButton.setText(R.string.continue_text);
            startButton.setVisibility(View.GONE);
            pauseAndContinueButton.setVisibility(View.VISIBLE);
            restartButton.setVisibility(View.VISIBLE);
            singleRestartButton.setVisibility(View.GONE);
        }

        void showSingleRestartButton() {
            startButton.setVisibility(View.GONE);
            pauseAndContinueButton.setVisibility(View.GONE);
            restartButton.setVisibility(View.GONE);
            singleRestartButton.setVisibility(View.VISIBLE);
        }

    }

    private class NormalViewHolder extends RecyclerView.ViewHolder {
        TextView currentStepTextView;
        TextView contentTextView;

        NormalViewHolder(View itemView) {
            super(itemView);
            currentStepTextView = itemView.findViewById(R.id.textView_coffee_detail_normal_current_step);
            contentTextView = itemView.findViewById(R.id.textView_coffee_detail_normal);
        }
    }

    private class CompletedViewHolder extends RecyclerView.ViewHolder {
        TextView currentStepTextView;
        TextView contentTextView;

        CompletedViewHolder(View itemView) {
            super(itemView);
            currentStepTextView = itemView.findViewById(R.id.textView_coffee_detail_completed_current_step);
            contentTextView = itemView.findViewById(R.id.textView_coffee_detail_completed);
        }
    }
}
