package be.ehb.student.jorisderijck.engine.scriptutils.jobs;

public class JobUtils implements IJobUtils {

    @Override
    public AtomicalJob createAtomicJob(String jobname)
    {
        return new AtomicalJob(jobname);
    }

    @Override
    public AtomicalJob createAtomicJobWithProperties(String jobname,
            JobProperty... properties)
    {
        AtomicalJob job = new AtomicalJob(jobname);
        for(JobProperty jp:properties)
        {
            job.addProperty(jp);
        }
        return job;
    }

    @Override
    public ComplexJob createComplexJob(String jobname)
    {
        return new ComplexJob(jobname);
    }

    @Override
    public ComplexJob createComplexJobWithJobs(String jobname,
            AbstractJob... jobs)
    {
        ComplexJob job = createComplexJob(jobname);
        for(AbstractJob j:jobs)
        {
            job.add(j);
        }
        return job;
    }




}
