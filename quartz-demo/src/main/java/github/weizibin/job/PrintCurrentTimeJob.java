package github.weizibin.job;

import github.weizibin.service.TimeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

public class PrintCurrentTimeJob extends QuartzJobBean
{
    @Autowired
    private TimeService timeService;

    private Log logger = LogFactory.getLog(getClass());

    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {
        logger.info("begin to execute task," + new Date());

        System.out.println(timeService.getCurrentTime());

        logger.info("end to execute task," + new Date());

    }
}
