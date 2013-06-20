package be.ehb.student.jorisderijck.engine.binds.facades;

import be.ehb.student.jorisderijck.engine.scriptutils.jobs.*;

public class ScriptJobUtils implements IJobUtils {

    private JobUtils jobutils;
    public static final AtomicalJob NOJOB = new AtomicalJob("none");
    public ScriptJobUtils(){
        jobutils = new JobUtils();
    }

    @Override
    public AtomicalJob createAtomicJob(String jobname)
    {
        return jobutils.createAtomicJob(jobname);
    }

    @Override
    public AtomicalJob createAtomicJobWithProperties(String jobname,
            JobProperty... properties)
    {
        return jobutils.createAtomicJobWithProperties(jobname, properties);
    }

    @Override
    public ComplexJob createComplexJob(String jobname)
    {
        return jobutils.createComplexJob(jobname);
    }

    @Override
    public ComplexJob createComplexJobWithJobs(String jobname,
            AbstractJob... jobs)
    {
        return jobutils.createComplexJobWithJobs(jobname, jobs);
    }
}
