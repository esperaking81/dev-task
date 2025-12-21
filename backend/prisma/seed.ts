import { PrismaPg } from '@prisma/adapter-pg';
import { PrismaClient } from './generated/client';
import * as bcrypt from 'bcrypt';

// Initialize Prisma with the same adapter config as your service
const adapter = new PrismaPg({
  connectionString: process.env.DATABASE_URL as string,
});

const prisma = new PrismaClient({ adapter });

async function main() {
  console.log('🌱 Starting seed...');

  // Clean existing data (optional - be careful!)
  await prisma.task.deleteMany();
  await prisma.tag.deleteMany();
  await prisma.user.deleteMany();

  // Create a test user
  const hashedPassword = await bcrypt.hash('password123', 10);

  const user = await prisma.user.create({
    data: {
      email: 'dev@devtask.com',
      password: hashedPassword,
      name: 'Dev User',
    },
  });

  console.log('✅ Created user:', user.email);

  // Create some tags
  const tagBackend = await prisma.tag.create({
    data: { name: 'Backend', color: '#3B82F6' },
  });

  const tagFrontend = await prisma.tag.create({
    data: { name: 'Frontend', color: '#10B981' },
  });

  const tagBug = await prisma.tag.create({
    data: { name: 'Bug', color: '#EF4444' },
  });

  const tagFeature = await prisma.tag.create({
    data: { name: 'Feature', color: '#8B5CF6' },
  });

  console.log('✅ Created tags');

  // Create a simple task
  await prisma.task.create({
    data: {
      title: 'Fix login bug',
      description: 'Users report timeout issues when logging in',
      status: 'todo',
      priority: 'high',
      dueDate: new Date(Date.now() + 3 * 24 * 60 * 60 * 1000), // 3 days from now
      userId: user.id,
      tags: {
        connect: [{ id: tagBackend.id }, { id: tagBug.id }],
      },
    },
  });

  // Create a task with subtasks (simulating AI breakdown)
  const parentTask = await prisma.task.create({
    data: {
      title: 'Build user dashboard',
      description:
        'Create a comprehensive dashboard for users to track their tasks',
      status: 'in_progress',
      priority: 'medium',
      dueDate: new Date(Date.now() + 14 * 24 * 60 * 60 * 1000), // 2 weeks from now
      userId: user.id,
      tags: {
        connect: [{ id: tagFrontend.id }, { id: tagFeature.id }],
      },
    },
  });

  // Create subtasks
  await prisma.task.createMany({
    data: [
      {
        title: 'Design dashboard layout',
        status: 'done',
        priority: 'medium',
        userId: user.id,
        parentId: parentTask.id,
        order: 0,
      },
      {
        title: 'Implement chart components',
        status: 'in_progress',
        priority: 'high',
        dueDate: new Date(Date.now() + 7 * 24 * 60 * 60 * 1000), // 1 week from now
        userId: user.id,
        parentId: parentTask.id,
        order: 1,
      },
      {
        title: 'Add filtering controls',
        status: 'todo',
        priority: 'low',
        userId: user.id,
        parentId: parentTask.id,
        order: 2,
      },
      {
        title: 'Write unit tests',
        status: 'todo',
        priority: 'medium',
        userId: user.id,
        parentId: parentTask.id,
        order: 3,
      },
    ],
  });

  console.log('✅ Created task with subtasks');

  // Create more sample tasks
  await prisma.task.createMany({
    data: [
      {
        title: 'Update API documentation',
        description: 'Add examples for new endpoints',
        status: 'todo',
        priority: 'low',
        dueDate: new Date(Date.now() + 5 * 24 * 60 * 60 * 1000), // 5 days from now
        userId: user.id,
      },
      {
        title: 'Optimize database queries',
        description: 'Some endpoints are slow with large datasets',
        status: 'todo',
        priority: 'high',
        dueDate: new Date(Date.now() + 10 * 24 * 60 * 60 * 1000), // 10 days from now
        userId: user.id,
      },
      {
        title: 'Setup CI/CD pipeline',
        description: 'Automate testing and deployment',
        status: 'done',
        priority: 'medium',
        userId: user.id,
      },
    ],
  });

  console.log('✅ Created additional tasks');

  // Create a task assigned to the user
  await prisma.task.create({
    data: {
      title: 'Review code changes',
      description: 'Review pull requests and provide feedback',
      status: 'todo',
      priority: 'high',
      dueDate: new Date(Date.now() + 2 * 24 * 60 * 60 * 1000), // 2 days from now
      userId: user.id,
      assigneeId: user.id, // Assign to self
      tags: {
        connect: [{ id: tagBackend.id }],
      },
    },
  });

  console.log('✅ Created assigned task');

  // Link tags to the additional tasks
  await prisma.task.update({
    where: {
      id: (await prisma.task.findFirst({
        where: { title: 'Update API documentation' },
      }))!.id,
    },
    data: { tags: { connect: { id: tagBackend.id } } },
  });

  console.log('🎉 Seed completed successfully!');
  console.log('\n📝 Test credentials:');
  console.log('   Email: dev@devtask.com');
  console.log('   Password: password123');
}

main()
  .catch((e) => {
    console.error('❌ Seed failed:', e);
    process.exit(1);
  })
  .finally(async () => {
    await prisma.$disconnect();
  });
